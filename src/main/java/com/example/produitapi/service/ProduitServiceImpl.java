package com.example.produitapi.service;

import com.example.produitapi.exceptions.models.ElementAlreadyExistsException;
import com.example.produitapi.exceptions.models.ElementNotFoundException;
import com.example.produitapi.mapper.impl.ProduitMapper;
import com.example.produitapi.mapper.impl.TypeProduitMapper;
import com.example.produitapi.models.dto.ProduitDTO;
import com.example.produitapi.models.dto.TypeProduitDTO;
import com.example.produitapi.models.entity.Produit;
import com.example.produitapi.models.entity.TypeProduit;
import com.example.produitapi.models.form.produit.ProduitForm;
import com.example.produitapi.repository.ProduitRepository;
import com.example.produitapi.repository.TypeProduitRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProduitServiceImpl implements BaseService<ProduitDTO, ProduitForm, ProduitForm, Long>{

    private final ProduitMapper mapper;
    private final ProduitRepository produitRepository;
    private final TypeProduitRepository typeProduitRepository;
    private final TypeProduitMapper tpMapper;

    public ProduitServiceImpl(ProduitMapper mapper, ProduitRepository produitRepository, TypeProduitRepository typeProduitRepository, TypeProduitMapper tpMapper) {
        this.mapper = mapper;
        this.produitRepository = produitRepository;
        this.typeProduitRepository = typeProduitRepository;
        this.tpMapper = tpMapper;
    }

    @Override
    public ProduitDTO getOne(Long id) {
        return produitRepository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(ElementNotFoundException::new);
    }

    @Override
    public List<ProduitDTO> getAll() {
        return produitRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProduitDTO insert(ProduitForm form) {

        if(produitRepository.existsByNom(form.getNom()))
            throw new ElementAlreadyExistsException();

        Produit produit = mapper.formToEntity( form );

        if( !typeProduitRepository.existsById(produit.getTypeProduit().getId()) )
             produit.setTypeProduit( typeProduitRepository.save(produit.getTypeProduit()) );

        produit = produitRepository.save(produit);

        return mapper.toDto(produit);
    }

    @Override
    public ProduitDTO delete(Long id) {

        Produit toDelete = produitRepository.findById(id)
                .orElseThrow(ElementAlreadyExistsException::new);

        produitRepository.delete(toDelete);

        return mapper.toDto(toDelete);
    }

    @Override
    public ProduitDTO update(Long id, ProduitForm form) {

        Produit toUpdate = produitRepository.findById(id)
                .orElseThrow(ElementNotFoundException::new);

        toUpdate.setNom(form.getNom());

        typeProduitRepository.findByNom(form.getNomType())
                .ifPresentOrElse(
                        (typeProduit) -> {
                            typeProduit.setPrix(form.getPrix());
                            toUpdate.setTypeProduit( typeProduitRepository.save(typeProduit) );
                        },
                        () -> {
                            TypeProduit typeProduit = new TypeProduit();
                            typeProduit.setNom(form.getNom());
                            typeProduit.setPrix(form.getPrix());
                            toUpdate.setTypeProduit( typeProduitRepository.save( typeProduit ) );
                        }
                );

        return mapper.toDto( produitRepository.save(toUpdate) );
    }

    public TypeProduitDTO getOneType(long id){
        return typeProduitRepository.findById(id)
                .map(tpMapper::toDto)
                .orElseThrow(ElementNotFoundException::new);
    }

    public List<TypeProduitDTO> getAllTypes(){
        return typeProduitRepository.findAll()
                .stream()
                .map(tpMapper::toDto)
                .collect(Collectors.toList());
    }

}
