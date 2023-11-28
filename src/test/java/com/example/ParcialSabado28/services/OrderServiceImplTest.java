package com.example.ParcialSabado28.services;

import com.example.ParcialSabado28.controller.OrderDto;
import com.example.ParcialSabado28.model.Order;
import com.example.ParcialSabado28.repository.OrderRepository;
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

public class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderDtoMapper orderDtoMapper;

    @Mock
    private OrderMapper orderMapper;

    @Mock
    private IdentifierRepository identifierRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllOrders() {
        Order order1 = new Order();
        Order order2 = new Order();
        OrderDto orderDto1 = new OrderDto();
        OrderDto orderDto2 = new OrderDto();

        when(orderRepository.findAll()).thenReturn(Arrays.asList(order1, order2));
        when(orderDtoMapper.apply(order1)).thenReturn(orderDto1);
        when(orderDtoMapper.apply(order2)).thenReturn(orderDto2);

        List<OrderDto> result = orderService.getAllOrders();

        assertEquals(2, result.size());
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    void createOrder() {
        OrderDto orderDto = new OrderDto();
        Order order = new Order();
        int nextId = 1;

        when(orderMapper.apply(orderDto)).thenReturn(order);
        when(identifierRepository.nextValue(Order.TABLE_NAME)).thenReturn(nextId);
        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> {
            Order savedOrder = invocation.getArgument(0);
            savedOrder.setOrderId(nextId);
            return savedOrder;
        });
        when(orderDtoMapper.apply(any(Order.class))).thenReturn(orderDto);

        OrderDto result = orderService.createOrder(orderDto);

        assertEquals(orderDto, result);
        verify(orderRepository, times(1)).save(any(Order.class));
        verify(identifierRepository, times(1)).nextValue(Order.TABLE_NAME);
    }

    @Test
    void updateOrder() {
        OrderDto orderDto = new OrderDto();
        Order order = new Order();

        when(orderMapper.apply(orderDto)).thenReturn(order);
        when(orderDtoMapper.apply(order)).thenReturn(orderDto);

        OrderDto result = orderService.updateOrder(orderDto);

        assertEquals(orderDto, result);
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    void deleteOrderById() {
        Order order = new Order();
        OrderDto orderDto = new OrderDto();
        Integer orderId = 1;

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
        when(orderDtoMapper.apply(order)).thenReturn(orderDto);

        OrderDto result = orderService.deleteOrderById(orderId);

        assertEquals(orderDto, result);
        verify(orderRepository, times(1)).delete(order);
    }

    @Test
    void findOrderById() {
        Order order = new Order();
        OrderDto orderDto = new OrderDto();
        Integer orderId = 1;

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
        when(orderDtoMapper.apply(order)).thenReturn(orderDto);

        OrderDto result = orderService.findOrderById(orderId);

        assertEquals(orderDto, result);
    }
}
