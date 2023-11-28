package com.example.ParcialSabado28.services;

import com.example.ParcialSabado28.controller.OrderDetailDto;
import com.example.ParcialSabado28.controller.OrderDetailIdDto;
import com.example.ParcialSabado28.model.Order;
import com.example.ParcialSabado28.model.OrderDetail;
import com.example.ParcialSabado28.model.OrderDetailId;
import com.example.ParcialSabado28.model.Product;
import com.example.ParcialSabado28.repository.OrderDetailRepository;
import com.example.ParcialSabado28.repository.IdentifierRepositoryImpl;
import com.example.ParcialSabado28.repository.OrderRepository;
import com.example.ParcialSabado28.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;
    private final IdentifierRepositoryImpl identifierRepositoryImpl;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public List<OrderDetailDto> findAll() {
        return orderDetailRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    public Optional<OrderDetailDto> findById(OrderDetailId id) {
        return orderDetailRepository.findById(id)
                .map(this::convertToDto);
    }

    public OrderDetailDto save(OrderDetailDto dto) {
        OrderDetail entity = convertToEntity(dto);
        OrderDetail savedEntity = orderDetailRepository.save(entity);
        return convertToDto(savedEntity);
    }

    public void deleteById(OrderDetailId id) {
        if (orderDetailRepository.existsById(id)) {
            orderDetailRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("No se encontró el detalle de la orden con ID: " + id);
        }
    }

    public OrderDetailDto update(OrderDetailId id, OrderDetailDto dto) {
        OrderDetail existingEntity = orderDetailRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró el detalle de la orden con ID: " + id));

        existingEntity.setUnitPrice(dto.getUnitPrice());
        existingEntity.setQuantity(dto.getQuantity());
        existingEntity.setDiscount(dto.getDiscount());

        OrderDetail updatedEntity = orderDetailRepository.save(existingEntity);
        return convertToDto(updatedEntity);
    }

    public OrderDetailDto convertToDto(OrderDetail entity) {
        OrderDetailDto dto = new OrderDetailDto();
        OrderDetailIdDto idDto = new OrderDetailIdDto();
        idDto.setOrderId(entity.getId().getOrderId());
        idDto.setProductId(entity.getId().getProductId());
        // Añade estas líneas
//        dto.setOrderId(entity.getId().getOrderId());
//        dto.setProductId(entity.getId().getProductId());
        dto.setId(idDto);
        dto.setUnitPrice(entity.getUnitPrice());
        dto.setQuantity(entity.getQuantity());
        dto.setDiscount(entity.getDiscount());
        return dto;
    }

    public OrderDetail convertToEntity(OrderDetailDto dto) {
        OrderDetail entity = new OrderDetail();
        OrderDetailId id = new OrderDetailId(dto.getId().getOrderId(), dto.getId().getProductId());
        entity.setId(id);

        Order order = orderRepository.findById(dto.getId().getOrderId())
                .orElseThrow(() -> new EntityNotFoundException("No se encontró la orden con ID: " + dto.getId().getOrderId()));
        entity.setOrder(order);

        Product product = productRepository.findById(dto.getId().getProductId())
                .orElseThrow(() -> new EntityNotFoundException("No se encontró el producto con ID: " + dto.getId().getProductId()));
        entity.setProduct(product);

        entity.setUnitPrice(dto.getUnitPrice());
        entity.setQuantity(dto.getQuantity());
        entity.setDiscount(dto.getDiscount());

        return entity;
    }
}
