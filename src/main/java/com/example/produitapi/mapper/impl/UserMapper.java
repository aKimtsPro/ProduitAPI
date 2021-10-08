package com.example.produitapi.mapper.impl;

import com.example.produitapi.mapper.Mapper;
import com.example.produitapi.models.dto.SmallUserDTO;
import com.example.produitapi.models.dto.UserDTO;
import com.example.produitapi.models.entity.Role;
import com.example.produitapi.models.entity.User;
import com.example.produitapi.models.form.user.UserForm;
import com.example.produitapi.repository.RoleRepository;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserMapper implements Mapper<UserDTO, UserForm, User> {

    private final RoleRepository roleRepository;
    private final AdresseMapper adresseMapper;

    public UserMapper(RoleRepository roleRepository, AdresseMapper adresseMapper) {
        this.roleRepository = roleRepository;
        this.adresseMapper = adresseMapper;
    }

    @Override
    public UserDTO toDto(User entity) {

        if(entity == null)
            return null;

        return UserDTO.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .moyenPayement(entity.getMoyenPayement())
                .adresse( adresseMapper.toDto(entity.getAdresse()) )
                .roles(
                        entity.getRoles().stream()
                                .map(Role::getNom)
                                .collect(Collectors.toList())
                )
                .accountNonExpired(entity.isCredentialsNonExpired())
                .accountNonLocked(entity.isAccountNonLocked())
                .credentialsNonExpired(entity.isCredentialsNonExpired())
                .enabled(entity.isEnabled())
                .build();
    }

    @Override
    public User toEntity(UserDTO dto) {
        throw new NotYetImplementedException();
    }

    @Override
    public User formToEntity(UserForm form) {
        if(form == null)
            return null;

        User user = new User();
        user.setUsername( form.getUsername() );
        user.setPassword( form.getPassword() );
        user.setEmail( form.getEmail() );
        user.setMoyenPayement( form.getMoyenPayement() );

        return user;
    }

    public SmallUserDTO toSmallDTO(User entity){
        if( entity == null )
            return null;

        return SmallUserDTO.builder()
                .username( entity.getUsername() )
                .email( entity.getEmail() )
                .moyenPayement( entity.getMoyenPayement() )
                .adresse( adresseMapper.toDto(entity.getAdresse()) )
                .build();

    }

}
