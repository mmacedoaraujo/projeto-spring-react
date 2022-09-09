package com.mmacedoaraujo.dsmeta.controllers;

import com.mmacedoaraujo.dsmeta.entities.Sale;
import com.mmacedoaraujo.dsmeta.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {
    @Autowired
    private SaleRepository saleRepo;

    @GetMapping
    public ResponseEntity<List<Sale>> findAll() {
        List<Sale> saleList = saleRepo.findAll();

        return ResponseEntity.ok().body(saleList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Sale> findById(@PathVariable Long id) {
        Optional<Sale> obj = saleRepo.findById(id);

        return ResponseEntity.ok().body(obj.get());
    }
}
