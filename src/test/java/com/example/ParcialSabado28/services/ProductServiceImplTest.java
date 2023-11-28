package com.example.ParcialSabado28.services;

import com.example.ParcialSabado28.controller.CustomProductDto;
import com.example.ParcialSabado28.controller.ProductDto;
import com.example.ParcialSabado28.model.Product;
import com.example.ParcialSabado28.repository.ProductRepository;
import com.example.ParcialSabado28.repository.IdentifierRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductDtoMapper productDtoMapper;

    @Mock
    private ProductMapper productMapper;

    @Mock
    private IdentifierRepository identifierRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllProducts() {
        Product product1 = new Product();
        Product product2 = new Product();
        ProductDto productDto1 = new ProductDto();
        ProductDto productDto2 = new ProductDto();

        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));
        when(productDtoMapper.apply(product1)).thenReturn(productDto1);
        when(productDtoMapper.apply(product2)).thenReturn(productDto2);

        List<ProductDto> result = productService.getAllProducts();

        assertEquals(2, result.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void createProduct() {
        ProductDto productDto = new ProductDto();
        Product product = new Product();
        int nextId = 1;

        when(productMapper.apply(productDto)).thenReturn(product);
        when(identifierRepository.nextValue(Product.TABLE_NAME)).thenReturn(nextId);
        when(productRepository.save(any(Product.class))).thenAnswer(invocation -> {
            Product savedProduct = invocation.getArgument(0);
            savedProduct.setProductId(nextId);
            return savedProduct;
        });
        when(productDtoMapper.apply(any(Product.class))).thenReturn(productDto);

        ProductDto result = productService.createProduct(productDto);

        assertEquals(productDto, result);
        verify(productRepository, times(1)).save(any(Product.class));
        verify(identifierRepository, times(1)).nextValue(Product.TABLE_NAME);
    }

    @Test
    void updateProduct() {
        ProductDto productDto = new ProductDto();
        Product product = new Product();

        when(productMapper.apply(productDto)).thenReturn(product);
        when(productDtoMapper.apply(product)).thenReturn(productDto);

        ProductDto result = productService.updateProduct(productDto);

        assertEquals(productDto, result);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void deleteProductById() {
        Product product = new Product();
        ProductDto productDto = new ProductDto();
        Integer productId = 1;

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(productDtoMapper.apply(product)).thenReturn(productDto);

        ProductDto result = productService.deleteProductById(productId);

        assertEquals(productDto, result);
        verify(productRepository, times(1)).delete(product);
    }

    @Test
    void findProductById() {
        Product product = new Product();
        ProductDto productDto = new ProductDto();
        Integer productId = 1;

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(productDtoMapper.apply(product)).thenReturn(productDto);

        ProductDto result = productService.findProductById(productId);

        assertEquals(productDto, result);
    }

    @Test
    void findProductsByCustomCriteria() {
        // Datos de prueba
        Product product1 = new Product();
        product1.setProductId(1);
        product1.setProductName("Producto1");
        product1.setUnitPrice(10.0);
        product1.setUnitsInStock(5);
        product1.setUnitsOnOrder(2);

        Product product2 = new Product();
        product2.setProductId(2);
        product2.setProductName("Producto2");
        product2.setUnitPrice(20.0);
        product2.setUnitsInStock(3);
        product2.setUnitsOnOrder(1);

        CustomProductDto customProductDto1 = new CustomProductDto();
        customProductDto1.setProductId(1);
        customProductDto1.setProductName("Producto1");
        customProductDto1.setUnitPrice(10.0);
        customProductDto1.setStockFuturo(7);

        CustomProductDto customProductDto2 = new CustomProductDto();
        customProductDto2.setProductId(2);
        customProductDto2.setProductName("Producto2");
        customProductDto2.setUnitPrice(20.0);
        customProductDto2.setStockFuturo(4);

        // Configurar mock
        when(productRepository.findCustomProducts(anyInt(), anyInt(), anyInt()))
                .thenReturn(Arrays.asList(product1, product2));

        // Llamar al método a probar
        List<CustomProductDto> result = productService.findProductsByCustomCriteria(1, 1, 1);

        // Verificar el resultado y las interacciones mock
        assertEquals(2, result.size());
        assertEquals(customProductDto1.getProductId(), result.get(0).getProductId());
        assertEquals(customProductDto1.getStockFuturo(), result.get(0).getStockFuturo());

        assertEquals(customProductDto2.getProductId(), result.get(1).getProductId());
        assertEquals(customProductDto2.getStockFuturo(), result.get(1).getStockFuturo());

        verify(productRepository, times(1)).findCustomProducts(anyInt(), anyInt(), anyInt());
    }

}
