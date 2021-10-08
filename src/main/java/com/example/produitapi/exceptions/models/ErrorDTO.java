package com.example.produitapi.exceptions.models;

import com.example.produitapi.exceptions.AdviserHandled;
import com.example.produitapi.exceptions.SkippedProperty;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ErrorDTO extends HashMap<String, Object> {

    public ErrorDTO(String message) {
        this.put("message", message);
        this.put("timestamp", Instant.now());
    }

    public ErrorDTO(String message, Map<? extends String, ?> info) {
        super(info);
        this.put("message", message);
        this.put("timestamp", Instant.now());
    }

    public static ErrorDTO of(Throwable ex){
        AdviserHandled annotation= ex.getClass().getAnnotation(AdviserHandled.class);
        if( annotation != null )
            return ErrorDTO.of(ex, annotation.skipFrom());

        return ErrorDTO.of(ex, RuntimeException.class);
    }

    public static ErrorDTO of(Throwable ex, Class<? extends Throwable> skipFrom){

        Map<String, Object> info = new HashMap<>();

        try{
            PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(ex.getClass()).getPropertyDescriptors();
            List<String> toSkip = getSkippedPropertiesName(skipFrom);
            for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {

                boolean isSkipped = false;
                for (int i = 0; !isSkipped && i < toSkip.size(); i++) {
                    if(toSkip.get(i).equals(propertyDescriptor.getName())){
                        isSkipped = true;
                        toSkip.remove(toSkip.get(i));
                    }
                }

                if( !isSkipped ){
                    try {
                        boolean skippedPropertyFromField = ex.getClass().getField( propertyDescriptor.getName() )
                                .getAnnotation( SkippedProperty.class ) == null;
                        boolean skippedPropertyFromMethod = propertyDescriptor.getReadMethod()
                                .getAnnotation( SkippedProperty.class ) == null;

                        // if neither the field nor the getter method is annotated with @SkippedProperty, include in the ErrorDTO
                        if( !skippedPropertyFromField || !skippedPropertyFromMethod )
                            info.put(propertyDescriptor.getName(), propertyDescriptor.getReadMethod().invoke( ex ));

                    }catch (InvocationTargetException | IllegalAccessException | NoSuchFieldException ignored){}
                }
            }

        }catch (IntrospectionException ignored){}

        return new ErrorDTO(ex.getMessage(), info);
    }

    public static List<String> getSkippedPropertiesName(Class<? extends Throwable> from) throws IntrospectionException {
        return Arrays.stream(Introspector.getBeanInfo(from).getPropertyDescriptors())
                .map(PropertyDescriptor::getName)
                .collect(Collectors.toList());
    }
}
