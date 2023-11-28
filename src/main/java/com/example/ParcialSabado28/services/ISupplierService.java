package com.example.ParcialSabado28.services;

import com.example.ParcialSabado28.controller.CustomerDto;
import com.example.ParcialSabado28.controller.SupplierDto;

import java.util.List;

public interface ISupplierService {
    List<SupplierDto> getAllSuppliers();

    SupplierDto createSupplier(SupplierDto supplierDto);

    SupplierDto updateSupplier(SupplierDto supplierDto);

    SupplierDto deleteSupplierById(Integer supplierId);

    SupplierDto findSupplierById(Integer supplierId);
}
