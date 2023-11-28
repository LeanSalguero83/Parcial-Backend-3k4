package com.example.ParcialSabado28.controller;

import com.example.ParcialSabado28.services.ISupplierService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SupplierController {

    ISupplierService supplierService;

    @GetMapping
    public ResponseEntity<List<SupplierDto>> getAllSuppliers() {
        List<SupplierDto> suppliers = supplierService.getAllSuppliers();
        return new ResponseEntity<>(suppliers, HttpStatus.OK); // 200 OK
    }

    @GetMapping("/{supplierId}")
    public ResponseEntity<SupplierDto> getSupplierById(@PathVariable Integer supplierId) {
        try {
            SupplierDto supplier = supplierService.findSupplierById(supplierId);
            return new ResponseEntity<>(supplier, HttpStatus.OK); // 200 OK
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        }
    }

    @PostMapping
    public ResponseEntity<SupplierDto> createSupplier(@RequestBody SupplierDto supplierDto) {
        SupplierDto createdSupplier = supplierService.createSupplier(supplierDto);
        return new ResponseEntity<>(createdSupplier, HttpStatus.CREATED); // 201 Created
    }

    @PutMapping("/{supplierId}")
    public ResponseEntity<SupplierDto> updateSupplier(@PathVariable Integer supplierId,
                                                      @RequestBody SupplierDto supplierDto) {
        try {
            supplierDto.setSupplierId(supplierId);
            SupplierDto updatedSupplier = supplierService.updateSupplier(supplierDto);
            return new ResponseEntity<>(updatedSupplier, HttpStatus.ACCEPTED); // 202 Accepted
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        }
    }

    @DeleteMapping("/{supplierId}")
    public ResponseEntity<SupplierDto> deleteSupplier(@PathVariable Integer supplierId) {
        try {
            SupplierDto deletedSupplier = supplierService.deleteSupplierById(supplierId);
            return new ResponseEntity<>(deletedSupplier, HttpStatus.ACCEPTED); // 202 Accepted
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        }
    }
}
