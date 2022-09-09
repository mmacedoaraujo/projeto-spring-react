package com.mmacedoaraujo.dsmeta.controllers;

import com.mmacedoaraujo.dsmeta.entities.Sale;
import com.mmacedoaraujo.dsmeta.repositories.SaleRepository;
import com.mmacedoaraujo.dsmeta.services.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {
    @Autowired
    private SaleRepository saleRepo;

    @Autowired
    private SmsService smsService;

    @GetMapping
    public Page<Sale> findAll(@RequestParam(value="minDate", defaultValue = "") String minDate, @RequestParam(value = "maxDate", defaultValue = "") String maxDate, Pageable pageable) {

        LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

        LocalDate min = minDate.equals("") ? today.minusDays(365) : LocalDate.parse(minDate);
        LocalDate max = maxDate.equals("") ? today : LocalDate.parse(maxDate);

        return saleRepo.findSales(min, max, pageable);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Sale> findById(@PathVariable Long id) {
        Optional<Sale> obj = saleRepo.findById(id);

        return ResponseEntity.ok().body(obj.get());
    }

    @GetMapping("/{id}/notification")
    public void notifySms(@PathVariable Long id) {
        smsService.sendSms(id);
    }
}
