package com.ingeneo.logistics.controllers;

import com.ingeneo.logistics.entities.LogisticsType;
import com.ingeneo.logistics.services.Impl.LogisticsTypeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logistics-type")
@RequiredArgsConstructor
public class LogisticsTypeController {
    private final LogisticsTypeServiceImpl logisticsTypeService;

    @GetMapping()
    public ResponseEntity<List<LogisticsType>> getAll(@RequestParam(required = false) String state) {
        return new ResponseEntity<>(logisticsTypeService.findAll(state), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LogisticsType> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(logisticsTypeService.findById(id));
    }

    @PostMapping()
    public ResponseEntity<LogisticsType> createLogisticsType(@RequestBody LogisticsType logisticsType) {
        return ResponseEntity.status(HttpStatus.CREATED).body(logisticsTypeService.create(logisticsType));
    }

    @PutMapping
    public ResponseEntity<LogisticsType> updateLogisiticsType(@RequestBody LogisticsType logisiticsType) {
        return new ResponseEntity<>(logisticsTypeService.update(logisiticsType), HttpStatus.OK);
    }
}
