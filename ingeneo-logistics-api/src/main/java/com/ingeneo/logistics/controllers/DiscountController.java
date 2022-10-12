package com.ingeneo.logistics.controllers;

import com.ingeneo.logistics.entities.Discount;
import com.ingeneo.logistics.services.DiscountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discounts")
@RequiredArgsConstructor
@Slf4j
public class DiscountController {
    private final DiscountService discountService;

    @GetMapping
    public ResponseEntity<List<Discount>> getAll(@RequestParam(required = false) String state) {
        log.info("fetching all discount from endpoint");
        return ResponseEntity.status(HttpStatus.OK).body(discountService.findAll(state));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Discount> getById(@PathVariable Long id) {
        log.info("Get discount by id from endpoint");
        return new ResponseEntity<>(discountService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Discount> save(@RequestBody Discount discount) {
        log.info("Saving discount from endpoint");
        return ResponseEntity.status(HttpStatus.CREATED).body(discountService.create(discount));
    }

    @PutMapping
    public ResponseEntity<Discount> update(@RequestBody Discount discount) {
        log.info("Updating discount from endpoint");
        return ResponseEntity.ok(discountService.update(discount));
    }
}
