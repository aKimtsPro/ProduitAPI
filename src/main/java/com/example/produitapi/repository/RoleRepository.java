package com.example.produitapi.repository;

import com.example.produitapi.models.entity.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Optional<Role> findByNom(String nom);
    boolean existsAllByNomIn(List<String> noms);
    List<Role> findAllByNomIsIn(List<String> noms);

}
