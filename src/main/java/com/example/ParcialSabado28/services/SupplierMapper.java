package com.example.ParcialSabado28.services;

import com.example.ParcialSabado28.controller.SupplierDto;
import org.springframework.stereotype.Service;
import com.example.ParcialSabado28.model.Supplier;
import java.util.function.Function;

@Service

public class SupplierMapper implements Function<SupplierDto, Supplier> {
    @Override
    public Supplier apply(SupplierDto supplierDto) {
        Supplier supplier = new Supplier();

        supplier.setSupplierId(supplierDto.getSupplierId());
        supplier.setCompanyName(supplierDto.getCompanyName());
        supplier.setContactName(supplierDto.getContactName());
        supplier.setContactTitle(supplierDto.getContactTitle());
        supplier.setAddress(supplierDto.getAddress());
        supplier.setCity(supplierDto.getCity());
        supplier.setRegion(supplierDto.getRegion());
        supplier.setPostalCode(supplierDto.getPostalCode());
        supplier.setCountry(supplierDto.getCountry());
        supplier.setPhone(supplierDto.getPhone());
        supplier.setFax(supplierDto.getFax());
        supplier.setHomePage(supplierDto.getHomePage());

        return supplier;
    }
}
