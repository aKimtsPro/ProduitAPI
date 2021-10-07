package com.example.produitapi.models.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Table(name = "groupe")
@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Groupe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nom;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "group_roles",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id")
    )
    private List<Role> roles;

}