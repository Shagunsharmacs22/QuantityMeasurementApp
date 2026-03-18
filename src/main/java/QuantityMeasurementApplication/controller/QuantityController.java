package QuantityMeasurementApplication.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import QuantityMeasurementApplication.dto.QuantityDTO;
import QuantityMeasurementApplication.service.QuantityMeasurementService;

@RestController
@RequestMapping("/api/quantity")
public class QuantityController {

    private QuantityMeasurementService service;

    public QuantityController(QuantityMeasurementService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody QuantityDTO dto) {
        return ResponseEntity.ok(service.save(dto));
    }

    @GetMapping("/operation/{op}")
    public ResponseEntity<?> getByOperation(@PathVariable String op) {
        return ResponseEntity.ok(service.getByOperation(op));
    }
    
}