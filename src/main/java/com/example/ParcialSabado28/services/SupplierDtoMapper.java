package com.example.ParcialSabado28.services;

import com.example.ParcialSabado28.controller.SupplierDto;
import com.example.ParcialSabado28.model.Supplier;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class SupplierDtoMapper implements Function<Supplier, SupplierDto> {
    @Override
    public SupplierDto apply(Supplier supplier) {
        SupplierDto supplierDto = new SupplierDto();

        supplierDto.setSupplierId(supplier.getSupplierId());
        supplierDto.setCompanyName(supplier.getCompanyName());
        supplierDto.setContactName(supplier.getContactName());
        supplierDto.setContactTitle(supplier.getContactTitle());
        supplierDto.setAddress(supplier.getAddress());
        supplierDto.setCity(supplier.getCity());
        supplierDto.setRegion(supplier.getRegion());
        supplierDto.setPostalCode(supplier.getPostalCode());
        supplierDto.setCountry(supplier.getCountry());
        supplierDto.setPhone(supplier.getPhone());
        supplierDto.setFax(supplier.getFax());
        supplierDto.setHomePage(supplier.getHomePage());

        return supplierDto;
    }
}
