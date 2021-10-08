package com.example.produitapi.controller;

import com.example.produitapi.models.dto.CommandeDTO;
import com.example.produitapi.models.form.commande.AddRemoveCommandeForm;
import com.example.produitapi.models.form.commande.CommandeForm;
import com.example.produitapi.service.CommandeServiceImpl;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;

@Controller
@RequestMapping("/commande")
public class CommandeController {

    private final CommandeServiceImpl commandeService;

    public CommandeController(CommandeServiceImpl commandeService) {
        this.commandeService = commandeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommandeDTO> getOne(@PathVariable long id){
        return ResponseEntity.ok( commandeService.getOne(id) );
    }

    @GetMapping
    public ResponseEntity<List<CommandeDTO>> getOne(){
        return ResponseEntity.ok( commandeService.getAll() );
    }

    @PostMapping
    public ResponseEntity<CommandeDTO> insert(@Valid @RequestBody CommandeForm form){
        return ResponseEntity.ok( commandeService.insert(form) );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommandeDTO> delete(@PathVariable long id){
        return ResponseEntity.ok( commandeService.delete(id) );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommandeDTO> update(@PathVariable long id, @Valid @RequestBody CommandeForm form){
        return ResponseEntity.ok( commandeService.update(id, form) );
    }

    @PostMapping("/{id}/add")
    public ResponseEntity<CommandeDTO> addProduit(@PathVariable("id") long cmdId, @Valid @RequestBody AddRemoveCommandeForm form){
        return ResponseEntity.ok( commandeService.addProduit(cmdId, form) );
    }

    @PostMapping("/{id}/remove")
    public ResponseEntity<CommandeDTO> removeProduit(@PathVariable("id") long cmdId, @Valid @RequestBody AddRemoveCommandeForm form){
        return ResponseEntity.ok( commandeService.removeProduit(cmdId, form) );
    }

    @PatchMapping("/{id}/confirm")
    public ResponseEntity<CommandeDTO> confirmCommande(@PathVariable long id, @RequestParam(name = "estimation") String estimation){
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        return ResponseEntity.ok( commandeService.confirmCommande(id, LocalDateTime.from(formatter.parse(estimation))) );
    }

}
