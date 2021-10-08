package com.example.produitapi.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "type_produit")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TypeProduit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, unique = true)
    private String nom;
    @Column(nullable = false, columnDefinition = "DECIMAL(10,2)")
    private Double prix;
}
