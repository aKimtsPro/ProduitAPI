package com.example.produitapi.mapper.impl;

import com.example.produitapi.mapper.BaseMapper;
import com.example.produitapi.models.dto.CommandeLigneDTO;
import com.example.produitapi.models.entity.CommandeLigne;
import org.springframework.stereotype.Service;

@Service
public class CommandeLigneMapper implements BaseMapper<CommandeLigneDTO, CommandeLigne> {

    private final ProduitMapper produitMapper;

    public CommandeLigneMapper(ProduitMapper produitMapper) {
        this.produitMapper = produitMapper;
    }

    @Override
    public CommandeLigneDTO toDto(CommandeLigne entity) {
        if(entity == null)
            return null;

        return CommandeLigneDTO.builder()
                .produit( produitMapper.toDto(entity.getProduit()) )
                .qtt( entity.getQtt() )
                .build();
    }

    @Override
    public CommandeLigne toEntity(CommandeLigneDTO dto) {
        return null;
    }
}
