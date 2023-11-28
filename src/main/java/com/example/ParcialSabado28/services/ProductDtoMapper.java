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
public class ProductDtoMapper implements Function<Product, ProductDto> {

    SupplierRepository supplierRepository;
    CategoryRepository categoryRepository;

    @Override
    public ProductDto apply(Product product) {
        ProductDto newProductDto = new ProductDto();
        newProductDto.setProductId(product.getProductId());
        newProductDto.setProductName(product.getProductName());
        newProductDto.setSupplierId(product.getSupplier().getSupplierId());
        newProductDto.setCategoryId(product.getCategory().getCategoryId());
        newProductDto.setQuantityPerUnit(product.getQuantityPerUnit());
        newProductDto.setUnitPrice(product.getUnitPrice());
        newProductDto.setUnitsInStock(product.getUnitsInStock());
        newProductDto.setUnitsOnOrder(product.getUnitsOnOrder());
        newProductDto.setReorderLevel(product.getReorderLevel());
        newProductDto.setDiscontinued(product.getDiscontinued());
        return newProductDto;
    }
}
