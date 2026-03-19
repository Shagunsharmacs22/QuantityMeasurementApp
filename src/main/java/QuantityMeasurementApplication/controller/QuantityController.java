package QuantityMeasurementApplication.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import QuantityMeasurementApplication.dto.QuantityDTO;
import QuantityMeasurementApplication.service.QuantityMeasurementService;
import QuantityMeasurementApplication.entity.QuantityMeasurementEntity;

import java.util.List;

@RestController
@RequestMapping("/api/quantity")
public class QuantityController {

	private final QuantityMeasurementService service;
    public QuantityController(QuantityMeasurementService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody QuantityDTO dto) {
        return ResponseEntity.ok(service.save(dto));
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/operation/{op}")
    public ResponseEntity<?> getByOperation(@PathVariable String op) {
        return ResponseEntity.ok(service.getByOperation(op));
    }

    @PutMapping({"/{id}", "/{id}/"})
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody QuantityDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @PatchMapping({"/{id}", "/{id}/"})
    public ResponseEntity<?> partialUpdate(@PathVariable Long id, @RequestBody QuantityDTO dto) {
        return ResponseEntity.ok(service.partialUpdate(id, dto));
    }

    @DeleteMapping({"/{id}", "/{id}/"})
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/test")
    public String test() {
        return "Working";
    }
}