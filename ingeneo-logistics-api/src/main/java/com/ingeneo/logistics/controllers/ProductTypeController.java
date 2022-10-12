package com.ingeneo.logistics.controllers;

import com.ingeneo.logistics.entities.ProductType;
import com.ingeneo.logistics.services.Impl.ProductTypeServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product-type")
@RequiredArgsConstructor
@Slf4j
public class ProductTypeController {
    private final ProductTypeServiceImpl productTypeService;

    @GetMapping
    public ResponseEntity<List<ProductType>> getAll(@RequestParam Optional<String> state) {
        log.info("method ProductTypeController.getAll with param {}", state);
        return ResponseEntity.status(HttpStatus.OK).body(productTypeService.findAll(state.orElse("ACTIVE")));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductType> getById(@PathVariable Long id) {
        log.info("method ProductTypeController.getById with id {}", id);
        return new ResponseEntity<>(productTypeService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductType> save(@RequestBody ProductType productType) {
        log.info("method save of ProductTypeController with data {}", productType.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(productTypeService.create(productType));
    }

    @PutMapping
    public ResponseEntity<ProductType> update(@RequestBody ProductType productType) {
        log.info("method update of ProductTypeController with data {}", productType.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(productTypeService.create(productType));
    }
}
