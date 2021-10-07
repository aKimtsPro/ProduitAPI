package com.example.produitapi.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "role_user")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleUser {

    @Id
    @GeneratedValue
    private long id;
    
    @ManyToOne
    private Role role;

    @ManyToOne
    private User user;

    @Column(nullable = false)
    private LocalDateTime dateDebut;

    private LocalDateTime dateFin;



}
