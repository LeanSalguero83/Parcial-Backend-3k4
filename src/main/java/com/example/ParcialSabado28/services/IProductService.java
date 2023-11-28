package com.example.ParcialSabado28.services;

import com.example.ParcialSabado28.controller.CustomProductDto;
import com.example.ParcialSabado28.controller.ProductDto;

import java.util.List;

public interface IProductService {
    List<ProductDto> getAllProducts();

    ProductDto createProduct(ProductDto productDto);

    ProductDto updateProduct(ProductDto productDto);

    ProductDto deleteProductById(Integer productId);

    ProductDto findProductById(Integer productId);

    List<CustomProductDto> findProductsByCustomCriteria(Integer supplierId, Integer categoryId, Integer stockMin);
}
