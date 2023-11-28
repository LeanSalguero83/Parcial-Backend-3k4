package com.example.ParcialSabado28.services;

import com.example.ParcialSabado28.controller.OrderDetailDto;
import com.example.ParcialSabado28.controller.OrderDetailIdDto;
import com.example.ParcialSabado28.model.Order;
import com.example.ParcialSabado28.model.OrderDetail;
import com.example.ParcialSabado28.model.OrderDetailId;
import com.example.ParcialSabado28.model.Product;
import com.example.ParcialSabado28.repository.IdentifierRepositoryImpl;
import com.example.ParcialSabado28.repository.OrderDetailRepository;
import com.example.ParcialSabado28.repository.OrderRepository;
import com.example.ParcialSabado28.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class OrderDetailServiceTest {

    @Mock
    private OrderDetailRepository orderDetailRepository;

    @Mock
    private IdentifierRepositoryImpl identifierRepositoryImpl;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderDetailService orderDetailService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById() {
        OrderDetailId id = new OrderDetailId(1, 1);
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setId(id);

        when(orderDetailRepository.findById(id)).thenReturn(Optional.of(orderDetail));

        Optional<OrderDetailDto> result = orderDetailService.findById(id);

        assertEquals(1, result.get().getId().getOrderId());
        assertEquals(1, result.get().getId().getProductId());
    }
    @Test
    void testDeleteByIdNotFound() {
        OrderDetailId id = new OrderDetailId(1, 1);

        when(orderDetailRepository.existsById(id)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> orderDetailService.deleteById(id));
    }@Test
    void testFindAll() {
        List<OrderDetail> orderDetails = new ArrayList<>();
        OrderDetail detail1 = new OrderDetail();
        detail1.setId(new OrderDetailId(1, 1));
        OrderDetail detail2 = new OrderDetail();
        detail2.setId(new OrderDetailId(2, 2));
        orderDetails.add(detail1);
        orderDetails.add(detail2);

        when(orderDetailRepository.findAll()).thenReturn(orderDetails);

        List<OrderDetailDto> results = orderDetailService.findAll();

        assertEquals(2, results.size());
        assertEquals(1, results.get(0).getId().getOrderId());
        assertEquals(2, results.get(1).getId().getOrderId());
    }
    @Test
    void testSave() {
        // Preparar datos
        OrderDetailDto dto = new OrderDetailDto();
        OrderDetailIdDto idDto = new OrderDetailIdDto();
        idDto.setOrderId(10436); // Establecer el orderId
        idDto.setProductId(22); // Establecer el productId
        dto.setId(idDto);
        dto.setUnitPrice(50.0);
        dto.setQuantity(5);
        dto.setDiscount(0.1);

        OrderDetail expectedEntity = new OrderDetail();
        OrderDetailId id = new OrderDetailId(10436, 22);
        expectedEntity.setId(id);
        expectedEntity.setUnitPrice(50.0);
        expectedEntity.setQuantity(5);
        expectedEntity.setDiscount(0.1);

        // Mocking
        when(productRepository.findById(22)).thenReturn(Optional.of(new Product()));
        when(orderRepository.findById(10436)).thenReturn(Optional.of(new Order()));
        when(orderDetailRepository.save(any(OrderDetail.class))).thenReturn(expectedEntity);

        // Ejecución
        OrderDetailDto resultDto = orderDetailService.save(dto);

        // Verificación
        assertEquals(idDto.getOrderId(), resultDto.getId().getOrderId());
        assertEquals(idDto.getProductId(), resultDto.getId().getProductId());
        assertEquals(dto.getUnitPrice(), resultDto.getUnitPrice());
        assertEquals(dto.getQuantity(), resultDto.getQuantity());
        assertEquals(dto.getDiscount(), resultDto.getDiscount());
    }

    @Test
    void testUpdate() {
        OrderDetailId id = new OrderDetailId(1, 1);
        OrderDetailDto dto = new OrderDetailDto(new OrderDetailIdDto(1, 1), 20.0, 2, 0.0);
        OrderDetail existingEntity = new OrderDetail(new OrderDetailId(1, 1), null, null, 10.0, 1, 0.0);

        when(orderDetailRepository.findById(id)).thenReturn(Optional.of(existingEntity));
        when(orderDetailRepository.save(any(OrderDetail.class))).thenReturn(existingEntity);

        OrderDetailDto updatedDto = orderDetailService.update(id, dto);

        assertEquals(dto.getUnitPrice(), updatedDto.getUnitPrice());
        assertEquals(dto.getQuantity(), updatedDto.getQuantity());
    }



}