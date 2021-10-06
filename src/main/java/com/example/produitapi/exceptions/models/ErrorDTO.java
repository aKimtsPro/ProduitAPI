package com.example.produitapi.exceptions.models;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

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
}
