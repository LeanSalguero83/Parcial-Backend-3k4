package com.example.ParcialSabado28.services;

import com.example.ParcialSabado28.controller.ProductDto;
import com.example.ParcialSabado28.model.Product;
import com.example.ParcialSabado28.repository.CategoryRepository;
import com.example.ParcialSabado28.repository.SupplierRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductMapper implements Function<ProductDto, Product> {

    SupplierRepository supplierRepository;
    CategoryRepository categoryRepository;

    @Override
    public Product apply(ProductDto productDto) {
        Product newProduct = new Product();
        newProduct.setProductName(productDto.getProductName());
        newProduct.setSupplier(supplierRepository.findById(productDto.getSupplierId()).orElse(null));
        newProduct.setCategory(categoryRepository.findById(productDto.getCategoryId()).orElse(null));
        newProduct.setQuantityPerUnit(productDto.getQuantityPerUnit());
        newProduct.setUnitPrice(productDto.getUnitPrice());
        newProduct.setUnitsInStock(productDto.getUnitsInStock());
        newProduct.setUnitsOnOrder(productDto.getUnitsOnOrder());
        newProduct.setReorderLevel(productDto.getReorderLevel());
        newProduct.setDiscontinued(productDto.getDiscontinued());
        return newProduct;
    }
}
