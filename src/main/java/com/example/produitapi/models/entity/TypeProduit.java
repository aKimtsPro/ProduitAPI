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
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String nom;
    @Column(nullable = false, columnDefinition = "DECIMAL(10,2)")
    private Double prix;
}
