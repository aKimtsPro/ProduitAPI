package com.example.produitapi.repository;

import com.example.produitapi.models.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepository extends JpaRepository<Produit, Long> {

    boolean existsByNom(String nom);

}
