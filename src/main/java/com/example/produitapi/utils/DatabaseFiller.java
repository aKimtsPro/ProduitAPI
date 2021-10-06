package com.example.produitapi.utils;

import com.example.produitapi.models.entity.Role;
import com.example.produitapi.models.entity.User;
import com.example.produitapi.repository.RoleRepository;
import com.example.produitapi.repository.UserRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseFiller implements InitializingBean {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public DatabaseFiller(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder encoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        Role admin = new Role();
        admin.setNom("ADMIN");
        Role user = new Role();
        user.setNom("USER");

        roleRepository.saveAll(List.of(admin, user));

        userRepository.saveAll(
                List.of(
                        new User(0L, "admin", encoder.encode("pass"),List.of(admin), true, true, true, true),
                        new User(0L, "user", encoder.encode("pass"),List.of(user), true, true, true, true)
                )
        );
    }
}
