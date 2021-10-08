package com.example.produitapi.mapper.impl;

import com.example.produitapi.mapper.Mapper;
import com.example.produitapi.models.dto.AdresseDTO;
import com.example.produitapi.models.entity.Adresse;
import com.example.produitapi.models.form.AdresseForm;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.stereotype.Service;

@Service
public class AdresseMapper implements Mapper<AdresseDTO, AdresseForm, Adresse> {
    @Override
    public AdresseDTO toDto(Adresse entity) {
        if( entity == null )
            return null;

        return AdresseDTO.builder()
                .ville(entity.getVille())
                .codePostal(entity.getCodePostal())
                .pays(entity.getPays())
                .numero(entity.getNumero())
                .rue(entity.getRue())
                .build();
    }

    @Override
    public Adresse toEntity(AdresseDTO dto) {
        throw new NotYetImplementedException();
    }

    @Override
    public Adresse formToEntity(AdresseForm form) {

        if( form == null )
            return null;

        Adresse adresse = new Adresse();

        adresse.setCodePostal(form.getCodePostal());
        adresse.setVille(form.getVille());
        adresse.setNumero(form.getNumero());
        adresse.setRue(form.getRue());
        adresse.setPays(form.getPays());

        return adresse;
    }
}
