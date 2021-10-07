package com.example.produitapi.utils;

import com.example.produitapi.models.entity.Adresse;
import com.example.produitapi.models.entity.Role;
import com.example.produitapi.models.entity.RoleUser;
import com.example.produitapi.models.entity.User;
import com.example.produitapi.repository.RoleRepository;
import com.example.produitapi.repository.RoleUserRepository;
import com.example.produitapi.repository.UserRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class DatabaseFiller implements InitializingBean {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final RoleUserRepository roleUserRepository;
    private final PasswordEncoder encoder;

    public DatabaseFiller(RoleRepository roleRepository, UserRepository userRepository, RoleUserRepository roleUserRepository, PasswordEncoder encoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.roleUserRepository = roleUserRepository;
        this.encoder = encoder;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        Role admin = new Role();
        admin.setNom("ADMIN");
        Role user = new Role();
        user.setNom("USER");

        roleRepository.saveAll(List.of(admin, user));

        User adminUser = new User(
                0L,
                "admin",
                encoder.encode("pass"),
                "email",
                "visa",
                new Adresse(0L, "", "", 6, 6, ""),
                null,
                null,
                null,
                true,
                true,
                true,
                true);

        User userUser = new User(
                0L,
                "user",
                encoder.encode("pass"),
                "email",
                "visa",
                new Adresse(0L, "", "", 6, 6, ""),
                null,
                null,
                null,
                true,
                true,
                true,
                true);

        userRepository.saveAll(
                List.of(adminUser, userUser)
        );

        List<RoleUser> adminRoles = List.of(
                new RoleUser(0L, admin, adminUser, LocalDateTime.now(), null),
                new RoleUser(0L, user, adminUser, LocalDateTime.now(), null),
                new RoleUser(0L, user, userUser, LocalDateTime.now(), null)
        );

        roleUserRepository.saveAll(adminRoles);

    }
}
