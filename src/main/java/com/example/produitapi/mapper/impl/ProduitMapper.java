package com.example.produitapi.mapper.impl;

import com.example.produitapi.mapper.Mapper;
import com.example.produitapi.models.dto.ProduitDTO;
import com.example.produitapi.models.entity.Produit;
import com.example.produitapi.models.entity.TypeProduit;
import com.example.produitapi.models.form.produit.ProduitForm;
import com.example.produitapi.repository.TypeProduitRepository;
import org.springframework.stereotype.Service;

@Service
public class ProduitMapper implements Mapper<ProduitDTO, ProduitForm, Produit> {

    private final TypeProduitMapper tpMapper;
    private final TypeProduitRepository tpRepository;

    public ProduitMapper(TypeProduitMapper tpMapper, TypeProduitRepository tpRepository) {
        this.tpMapper = tpMapper;
        this.tpRepository = tpRepository;
    }

    @Override
    public ProduitDTO toDto(Produit entity) {
        if(entity == null)
            return null;


        return ProduitDTO.builder()
                .id(entity.getId())
                .nom(entity.getNom())
                .typeProduit(tpMapper.toDto(entity.getTypeProduit()))
                .build();
    }

    @Override
    public Produit toEntity(ProduitDTO dto) {
        if( dto == null )
            return null;

        Produit p = new Produit();

        p.setId(dto.getId());
        p.setNom(dto.getNom());
        p.setTypeProduit(tpMapper.toEntity(dto.getTypeProduit()));

        return p;
    }

    @Override
    public Produit formToEntity(ProduitForm form) {
        Produit p = new Produit();

        p.setNom(form.getNom());

        tpRepository.findByNom(form.getNomType())
                .ifPresentOrElse(
                        p::setTypeProduit,
                        () -> {
                            TypeProduit typeProduit = new TypeProduit();
                            typeProduit.setNom(form.getNomType());
                            typeProduit.setPrix(form.getPrix());
                            p.setTypeProduit( typeProduit );
                        }
                );

        return p;
    }
}
