package com.example.produitapi.mapper.impl;

import com.example.produitapi.exceptions.models.ElementNotFoundException;
import com.example.produitapi.mapper.Mapper;
import com.example.produitapi.models.dto.CommandeDTO;
import com.example.produitapi.models.entity.Commande;
import com.example.produitapi.models.form.commande.CommandeForm;
import com.example.produitapi.repository.UserRepository;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class CommandeMapper implements Mapper<CommandeDTO, CommandeForm, Commande> {

    private final AdresseMapper adresseMapper;
    private final UserMapper userMapper;
    private final CommandeLigneMapper commandeLigneMapper;
    private final UserRepository userRepository;

    public CommandeMapper(AdresseMapper adresseMapper, UserMapper userMapper, CommandeLigneMapper commandeLigneMapper, UserRepository userRepository) {
        this.adresseMapper = adresseMapper;
        this.userMapper = userMapper;
        this.commandeLigneMapper = commandeLigneMapper;
        this.userRepository = userRepository;
    }

    @Override
    public CommandeDTO toDto(Commande entity) {
        if( entity == null )
            return null;

        return CommandeDTO.builder()
                .id(entity.getId())
                .adresse(adresseMapper.toDto(entity.getAdresse()))
                .dateLivraison(entity.getDateLivraison())
                .dateConfirmation(entity.getDateConfirmation())
                .realisePar( userMapper.toSmallDTO(entity.getUser()) )
                .lignes(
                        entity.getCommandeLignes()
                                .stream()
                                .map(commandeLigneMapper::toDto)
                                .collect(Collectors.toList())
                )
                .build();
    }

    @Override
    public Commande toEntity(CommandeDTO dto) {
        throw new NotYetImplementedException();
    }

    @Override
    public Commande formToEntity(CommandeForm form) {

        Commande cmd = new Commande();

        cmd.setUser( userRepository.findById(form.getUserId()).orElseThrow(ElementNotFoundException::new) );
        cmd.setAdresse( adresseMapper.formToEntity(form.getAdresse()) );
        cmd.setCommandeLignes(new ArrayList<>());

        return cmd;
    }
}
