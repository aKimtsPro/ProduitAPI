package com.example.produitapi.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "commande")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Commande {

    @Id
    @GeneratedValue
    private Long id;
    private LocalDateTime dateConfirmation;
    private LocalDateTime dateLivraison;

    @ManyToOne
    @JoinColumn(name = "adresse_id")
    private Adresse adresse;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "commande", orphanRemoval = true)
    private List<CommandeLigne> commandeLignes;

}
