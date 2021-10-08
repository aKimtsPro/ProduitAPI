package com.example.produitapi.utils;

import com.example.produitapi.models.entity.Adresse;
import com.example.produitapi.models.entity.Role;
import com.example.produitapi.models.entity.RoleUser;
import com.example.produitapi.models.entity.User;
import com.example.produitapi.repository.AdresseRepository;
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
    private final AdresseRepository adresseRepository;
    private final PasswordEncoder encoder;

    public DatabaseFiller(RoleRepository roleRepository, UserRepository userRepository, RoleUserRepository roleUserRepository, AdresseRepository adresseRepository, PasswordEncoder encoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.roleUserRepository = roleUserRepository;
        this.adresseRepository = adresseRepository;
        this.encoder = encoder;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        Role admin = new Role();
        admin.setNom("ADMIN");
        Role user = new Role();
        user.setNom("USER");

        admin = roleRepository.save(admin);
        user = roleRepository.save(user);

        Adresse adresse = new Adresse(0L, "rue de Bruxelles", "namur", 10, 5500, "Belgique");
        adresse = adresseRepository.save(adresse);

        User adminUser = new User(
                0L,
                "admin",
                encoder.encode("pass"),
                "email",
                "visa",
                adresse,
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
                adresse,
                null,
                null,
                null,
                true,
                true,
                true,
                true);

       adminUser = userRepository.save(adminUser);
       userUser = userRepository.save(userUser);

        List<RoleUser> adminRoles = List.of(
                new RoleUser(0L, admin, adminUser, LocalDateTime.now(), null),
                new RoleUser(0L, user, adminUser, LocalDateTime.now(), null),
                new RoleUser(0L, user, userUser, LocalDateTime.now(), null)
        );

        roleUserRepository.saveAll(adminRoles);

    }
}
