package com.example.ParcialSabado28.services;

import com.example.ParcialSabado28.controller.CreateOrderRequestDto;
import com.example.ParcialSabado28.controller.CustomProductDto;
import com.example.ParcialSabado28.controller.OrderDto;
import com.example.ParcialSabado28.controller.ProductDto;
import com.example.ParcialSabado28.model.Order;
import com.example.ParcialSabado28.model.Product;
import com.example.ParcialSabado28.repository.ProductRepository;
import com.example.ParcialSabado28.repository.IdentifierRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

    ProductRepository productRepository;
    ProductDtoMapper productDtoMapper;
    ProductMapper productMapper;
    IdentifierRepository identifierRepository;
    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products
                .stream()
                .map(productDtoMapper)
                .toList();
    }
    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = Optional.of(productDto).map(productMapper).orElseThrow();
        // Obtenemos un nuevo identificador para la tabla Product.
        int productId = identifierRepository.nextValue(Product.TABLE_NAME);
        // Asignamos el nuevo identificador a Product.
        product.setProductId(productId);
        Product savedProduct = productRepository.save(product);
        return productDtoMapper.apply(savedProduct);
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        Product product = Optional.of(productDto).map(productMapper).orElseThrow();
        productRepository.save(product);
        return productDtoMapper.apply(product);
    }

    @Override
    public ProductDto deleteProductById(Integer productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        ProductDto deleted = optionalProduct.map(productDtoMapper).orElseThrow();
        optionalProduct.ifPresent(productRepository::delete);
        return deleted;
    }
    @Override
    public ProductDto findProductById(Integer productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        return optionalProduct.map(productDtoMapper).orElseThrow();
    }
    public List<CustomProductDto> findProductsByCustomCriteria(Integer supplierId, Integer categoryId, Integer stockMin) {
        List<Product> products = productRepository.findCustomProducts(supplierId, categoryId, stockMin);
        return products.stream()
                .map(product -> {
                    CustomProductDto dto = new CustomProductDto();
                    dto.setProductId(product.getProductId());
                    dto.setProductName(product.getProductName());
                    dto.setUnitPrice(product.getUnitPrice());
                    dto.setStockFuturo(product.getUnitsInStock() + product.getUnitsOnOrder());
                    return dto;
                })
                .toList();
    }

    public List<CustomProductDto> findProductsBySupplierCategoryAndStock(Integer supplierId, Integer categoryId, Integer expectedStock) {
        return productRepository.findAll()
                .stream()
                .filter(product -> product.getSupplier().getSupplierId().equals(supplierId))
                .filter(product -> product.getCategory().getCategoryId().equals(categoryId))
                .filter(product -> (product.getUnitsInStock() + product.getUnitsOnOrder()) < expectedStock)
                .filter(product -> !"1".equals(product.getDiscontinued()))
                .map(product -> new CustomProductDto(
                        product.getProductId(),
                        product.getProductName(),
                        product.getUnitsInStock() + product.getUnitsOnOrder(),
                        product.getUnitPrice()))
                .sorted(Comparator.comparingInt(CustomProductDto::getStockFuturo))
                .collect(Collectors.toList());
    }

}
