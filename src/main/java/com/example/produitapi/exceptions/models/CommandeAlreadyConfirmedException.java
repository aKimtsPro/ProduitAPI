package com.example.produitapi.exceptions.models;

import com.example.produitapi.exceptions.annotation.AdviserHandled;

import java.time.LocalDateTime;

@AdviserHandled
public class CommandeAlreadyConfirmedException extends RuntimeException {

    public final LocalDateTime dateLivraison;

    public CommandeAlreadyConfirmedException(LocalDateTime dateLivraison) {
        super("Cette commande a déjà été confirmée. "+
                (dateLivraison.isAfter(LocalDateTime.now()) ?
                        "Elle sera livrée à ce moment: {"+dateLivraison+"}" :
                        "Elle a été livrée à ce moment: {"+dateLivraison+"}")
        );
        this.dateLivraison = dateLivraison;
    }

    public LocalDateTime getDateLivraison() {
        return dateLivraison;
    }
}
