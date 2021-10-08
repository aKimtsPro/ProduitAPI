package com.example.produitapi.service;

import com.example.produitapi.exceptions.models.CommandeAlreadyConfirmedException;
import com.example.produitapi.exceptions.models.ElementNotFoundException;
import com.example.produitapi.exceptions.models.InvalidQttException;
import com.example.produitapi.mapper.impl.CommandeMapper;
import com.example.produitapi.models.dto.CommandeDTO;
import com.example.produitapi.models.entity.Commande;
import com.example.produitapi.models.entity.CommandeLigne;
import com.example.produitapi.models.entity.Produit;
import com.example.produitapi.models.form.commande.AddRemoveCommandeForm;
import com.example.produitapi.models.form.commande.CommandeForm;
import com.example.produitapi.repository.AdresseRepository;
import com.example.produitapi.repository.CommandeLigneRepository;
import com.example.produitapi.repository.CommandeRepository;
import com.example.produitapi.repository.ProduitRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommandeServiceImpl implements BaseService<CommandeDTO, CommandeForm, CommandeForm, Long> {

    private final CommandeRepository commandeRepository;
    private final CommandeLigneRepository commandeLigneRepository;
    private final ProduitRepository produitRepository;
    private final AdresseRepository adresseRepository;
    private final CommandeMapper commandeMapper;

    public CommandeServiceImpl(CommandeRepository commandeRepository, CommandeLigneRepository commandeLigneRepository, ProduitRepository produitRepository, AdresseRepository adresseRepository, CommandeMapper commandeMapper) {
        this.commandeRepository = commandeRepository;
        this.commandeLigneRepository = commandeLigneRepository;
        this.produitRepository = produitRepository;
        this.adresseRepository = adresseRepository;
        this.commandeMapper = commandeMapper;
    }

    @Override
    public CommandeDTO getOne(Long id) {
        if(id == null)
            throw new IllegalArgumentException("commande id should not be null");

        return commandeRepository.findById(id)
                .map(commandeMapper::toDto)
                .orElseThrow(ElementNotFoundException::new);
    }

    @Override
    public List<CommandeDTO> getAll() {
        return commandeRepository.findAll()
                .stream()
                .map(commandeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CommandeDTO insert(CommandeForm form) {
        Commande cmd = commandeMapper.formToEntity(form);
        cmd.setId(0L);

        // TODO : check if adress exists
        cmd.setAdresse( adresseRepository.save( cmd.getAdresse() ) );
        cmd = commandeRepository.save(cmd);
        return commandeMapper.toDto(cmd);
    }

    @Override
    public CommandeDTO delete(Long id) {

        if(id == null)
            return null;

        Commande cmd = commandeRepository.findById(id)
                .orElseThrow(ElementNotFoundException::new);

        commandeRepository.delete(cmd);

        return commandeMapper.toDto(cmd);
    }

    @Override
    public CommandeDTO update(Long id, CommandeForm form) {

        if(id == null)
            throw new IllegalArgumentException("commande id should not be null");

        Commande toUpdate = commandeRepository.findById(id)
                .orElseThrow(ElementNotFoundException::new);

        Commande updateInfo = commandeMapper.formToEntity(form);

        // TODO : check if adresse exists
        toUpdate.setAdresse( adresseRepository.save(updateInfo.getAdresse()) );
        toUpdate.setUser(updateInfo.getUser());

        toUpdate = commandeRepository.save(toUpdate);

        return commandeMapper.toDto(toUpdate);
    }


    public CommandeDTO addProduit( long cmdId, AddRemoveCommandeForm form){

        if(form.getQtt() == null)
            form.setQtt(1L);

        Commande cmd = commandeRepository.findById(cmdId)
                .orElseThrow(ElementNotFoundException::new);

        Produit toAdd = produitRepository.findById(form.getProduitId())
                .orElseThrow(ElementNotFoundException::new);

        cmd.getCommandeLignes()
                .stream()
                .filter((cl) -> cl.getProduit().getId() == toAdd.getId())
                .findFirst()
                .ifPresentOrElse(
                        (cl) -> {
                            cl.setQtt( cl.getQtt() + form.getQtt() );
                            commandeLigneRepository.save(cl);
                        },
                        () ->{
                            CommandeLigne cl = new CommandeLigne();
                            cl.setProduit(toAdd);
                            cl.setQtt(form.getQtt());
                            cl.setCommande(cmd);
                            commandeLigneRepository.save(cl);
                            cmd.getCommandeLignes().add(cl);
                        }
                );

        return commandeMapper.toDto(cmd);
    }

    public CommandeDTO removeProduit(Long cmdId, AddRemoveCommandeForm form){

        Commande cmd = commandeRepository.findById(cmdId)
                .orElseThrow(ElementNotFoundException::new);

        cmd.getCommandeLignes()
                .stream()
                .filter((c) -> c.getProduit().getId() == form.getProduitId())
                .findFirst()
                .ifPresentOrElse(
                        (cl) -> {
                            if(form.getQtt() == null){
                                commandeLigneRepository.delete(cl);
                                cmd.getCommandeLignes().remove(cl);
                            }
                            else if(cl.getQtt() >= form.getQtt()){
                                cl.setQtt(cl.getQtt() - form.getQtt());
                                cmd.getCommandeLignes().remove(cl);
                                cmd.getCommandeLignes().add( commandeLigneRepository.save(cl) );
                            }
                            else {
                                throw new InvalidQttException(cl.getQtt(), form.getQtt());
                            }
                        },
                        () -> {
                            throw new ElementNotFoundException();
                        }
                );

        return commandeMapper.toDto(cmd);

    }

    public CommandeDTO confirmCommande(long cmdId, LocalDateTime livraisonEstimation){

        if(livraisonEstimation.isBefore(LocalDateTime.now()))
            throw new IllegalArgumentException("La date de livraison devrait être future.");

        Commande cmd = commandeRepository.findById(cmdId)
                .orElseThrow(ElementNotFoundException::new);

        if(cmd.getDateLivraison() != null)
            throw new CommandeAlreadyConfirmedException(cmd.getDateLivraison());

        cmd.setDateLivraison(livraisonEstimation);
        cmd = commandeRepository.save(cmd);

        return commandeMapper.toDto(cmd);
    }

}

