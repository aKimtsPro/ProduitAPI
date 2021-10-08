package com.example.produitapi.mapper.impl;

import com.example.produitapi.mapper.BaseMapper;
import com.example.produitapi.models.dto.TypeProduitDTO;
import com.example.produitapi.models.entity.TypeProduit;
import org.springframework.stereotype.Service;

@Service
public class TypeProduitMapper implements BaseMapper<TypeProduitDTO, TypeProduit> {
    @Override
    public TypeProduitDTO toDto(TypeProduit entity) {
        if(entity == null)
            return null;

        return TypeProduitDTO.builder()
                .id(entity.getId())
                .nom(entity.getNom())
                .prix(entity.getPrix())
                .build();
    }

    @Override
    public TypeProduit toEntity(TypeProduitDTO dto) {
        if( dto == null)
            return null;

        TypeProduit tp = new TypeProduit();

        tp.setId(dto.getId());
        tp.setNom(dto.getNom());
        tp.setPrix(dto.getPrix());

        return tp;
    }
}
