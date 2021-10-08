package com.example.produitapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


// TODO : separation en patch de plusieurs fonctionnalité
// TODO : verification adresse existante
// TODO : finaliser commande
// TODO : ameliorer ElementNotFoundException
@SpringBootApplication
public class ProduitApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProduitApiApplication.class, args);
    }

}
