package com.example.produitapi.repository;

import com.example.produitapi.models.entity.TypeProduit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TypeProduitRepository  extends JpaRepository<TypeProduit, Long> {

    Optional<TypeProduit> findByNom(String nom);

}
