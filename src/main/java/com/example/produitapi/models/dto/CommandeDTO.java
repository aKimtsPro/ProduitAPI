package com.example.produitapi.models.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class CommandeDTO {

    private long id;
    private List<CommandeLigneDTO> lignes;
    private SmallUserDTO realisePar;
    private AdresseDTO adresse;
    private LocalDateTime dateLivraison;

}
