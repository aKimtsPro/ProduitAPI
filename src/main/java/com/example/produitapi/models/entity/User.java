package com.example.produitapi.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String moyenPayement;

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "adresse_id", nullable = false)
    private Adresse adresse;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Commande> commandes;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<RoleUser> roleUsers;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "user_groups",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "groups_id"))
    private List<Groupe> groups;



    @Column(nullable = false)
    private boolean accountNonExpired;
    @Column(nullable = false)
    private boolean accountNonLocked;
    @Column(nullable = false)
    private boolean credentialNonExpired;
    @Column(nullable = false)
    private boolean enabled;




    public List<Role> getRoles(){
        List<Role> groupRoles = groups.stream()
                .flatMap(group -> group.getRoles().stream())
                .collect(Collectors.toList());

        List<Role> directRoles = roleUsers.stream()
                .filter(roleUser -> roleUser.getDateFin() == null || roleUser.getDateFin().isAfter(LocalDateTime.now()))
                .map(RoleUser::getRole)
                .collect(Collectors.toList());

        List<Role> allRoles = new ArrayList<>(groupRoles);
        allRoles.addAll(directRoles);


        return allRoles.stream()
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return getRoles()
                .stream()
                .map((role) -> new SimpleGrantedAuthority(role.getNom()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
