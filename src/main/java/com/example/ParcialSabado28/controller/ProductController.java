package com.example.ParcialSabado28.controller;

import com.example.ParcialSabado28.services.IProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductController {

    IProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK); // 200 OK
    }

    @GetMapping
    public ResponseEntity<List<CustomProductDto>> getCustomProducts(
            @RequestParam Integer supplierId,
            @RequestParam Integer categoryId,
            @RequestParam Integer stockMin) {

            List<CustomProductDto> products = productService.findProductsByCustomCriteria(supplierId, categoryId, stockMin);
            if (products.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
            }
            return new ResponseEntity<>(products, HttpStatus.OK); // 200 OK

    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Integer productId) {
        try {
            ProductDto product = productService.findProductById(productId);
            return new ResponseEntity<>(product, HttpStatus.OK); // 200 OK
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        }
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        ProductDto createdProduct = productService.createProduct(productDto);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED); // 201 Created
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Integer productId,
                                                    @RequestBody ProductDto productDto) {
        try {
            productDto.setProductId(productId);
            ProductDto updatedProduct = productService.updateProduct(productDto);
            return new ResponseEntity<>(updatedProduct, HttpStatus.ACCEPTED); // 202 Accepted
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        }
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable Integer productId) {
        try {
            ProductDto deletedProduct = productService.deleteProductById(productId);
            return new ResponseEntity<>(deletedProduct, HttpStatus.ACCEPTED); // 202 Accepted
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        }
    }
}
