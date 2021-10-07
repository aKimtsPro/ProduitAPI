package com.example.produitapi.mapper;

import com.example.produitapi.models.dto.UserDTO;
import com.example.produitapi.models.entity.Role;
import com.example.produitapi.models.entity.User;
import com.example.produitapi.models.form.UserInsertForm;
import com.example.produitapi.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserMapper implements Mapper<UserDTO, UserInsertForm, User> {

    private final RoleRepository roleRepository;

    public UserMapper(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDTO toDto(User entity) {

        if(entity == null)
            return null;

        return UserDTO.builder()
                .id(entity.getId())
                .username(entity.getUsername())
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
        return null;
    }

    @Override
    public User formToEntity(UserInsertForm form) {
        if(form == null)
            return null;

        User user = new User();
        user.setUsername( form.getUsername() );
        user.setPassword( form.getPassword() );
        user.setEmail("email"); // TODO adaption
        user.setMoyenPayement("visa");

        return user;
    }

}
