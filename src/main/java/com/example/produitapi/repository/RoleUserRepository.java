package com.example.produitapi.repository;

import com.example.produitapi.models.entity.RoleUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleUserRepository extends JpaRepository<RoleUser, Long> {
}
