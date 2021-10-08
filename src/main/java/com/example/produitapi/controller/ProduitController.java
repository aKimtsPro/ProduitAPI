package com.example.produitapi.controller;

import com.example.produitapi.models.dto.ProduitDTO;
import com.example.produitapi.models.dto.TypeProduitDTO;
import com.example.produitapi.models.form.produit.ProduitForm;
import com.example.produitapi.service.ProduitServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/produit")
public class ProduitController {

    private final ProduitServiceImpl produitService;

    public ProduitController(ProduitServiceImpl produitService) {
        this.produitService = produitService;
    }

    @PostMapping(path = {"", "/", "/add"})
    public ResponseEntity<ProduitDTO> insert(@Valid @RequestBody ProduitForm form){
        return ResponseEntity.ok( produitService.insert(form) );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProduitDTO> getOne(@PathVariable long id){
        return ResponseEntity.ok( produitService.getOne(id) );
    }

    @GetMapping
    public ResponseEntity<List<ProduitDTO>> getAll(){
        return ResponseEntity.ok( produitService.getAll() );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProduitDTO> update(@PathVariable long id, @Valid @RequestBody ProduitForm form){
        return ResponseEntity.ok( produitService.update(id, form) );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProduitDTO> delete(@PathVariable long id){
        return ResponseEntity.ok( produitService.delete(id) );
    }

    @GetMapping("/type")
    public ResponseEntity<List<TypeProduitDTO>> getAllTypes(){
        return ResponseEntity.ok( produitService.getAllTypes() );
    }

    @GetMapping("/type/{id}")
    public ResponseEntity<TypeProduitDTO> getOneType(@PathVariable long id){
        return ResponseEntity.ok( produitService.getOneType(id) );
    }


}
