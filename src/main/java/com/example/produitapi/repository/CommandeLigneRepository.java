package com.example.produitapi.repository;

import com.example.produitapi.models.entity.CommandeLigne;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeLigneRepository extends JpaRepository<CommandeLigne, Long> {
}
