package com.example.produitapi.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "cmd_ligne")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommandeLigne {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private long qtt;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Commande commande;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Produit produit;


}
