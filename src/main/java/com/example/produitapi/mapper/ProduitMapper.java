package com.example.produitapi.mapper;

import com.example.produitapi.models.dto.ProduitDTO;
import com.example.produitapi.models.entity.Produit;
import com.example.produitapi.models.form.ProduitForm;

public class ProduitMapper implements Mapper<ProduitDTO, ProduitForm, Produit> {
    @Override
    public ProduitDTO toDto(Produit entity) {
        if(entity == null)
            return null;


        return null;
    }

    @Override
    public Produit toEntity(ProduitDTO dto) {
        return null;
    }

    @Override
    public Produit formToEntity(ProduitForm form) {
        return null;
    }
}
