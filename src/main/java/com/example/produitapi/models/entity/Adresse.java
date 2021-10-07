package com.example.produitapi.models.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "adresse")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Adresse {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String rue;
    @Column(nullable = false)
    private String ville;
    @Column(nullable = false)
    private int numero;
    @Column(nullable = false)
    private int codePostal;
    @Column(nullable = false)
    private String pays;

}
