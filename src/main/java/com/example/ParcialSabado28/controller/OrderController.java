package com.example.ParcialSabado28.controller;

import com.example.ParcialSabado28.services.IOrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderController {

    IOrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        List<OrderDto> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK); // 200 OK
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Integer orderId) {
        try {
            OrderDto order = orderService.findOrderById(orderId);
            return new ResponseEntity<>(order, HttpStatus.OK); // 200 OK
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        }
    }

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
        OrderDto createdOrder = orderService.createOrder(orderDto);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED); // 201 Created
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable Integer orderId,
                                                @RequestBody OrderDto orderDto) {
        try {
            orderDto.setOrderId(orderId);
            OrderDto updatedOrder = orderService.updateOrder(orderDto);
            return new ResponseEntity<>(updatedOrder, HttpStatus.ACCEPTED); // 202 Accepted
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        }
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<OrderDto> deleteOrder(@PathVariable Integer orderId) {
        try {
            OrderDto deletedOrder = orderService.deleteOrderById(orderId);
            return new ResponseEntity<>(deletedOrder, HttpStatus.ACCEPTED); // 202 Accepted
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        }
    }

    @PostMapping("/custom")
    public ResponseEntity<OrderDto> createCustomOrder(@RequestBody CreateOrderRequestDto createOrderRequestDto) {
        try {
            OrderDto createdOrder = orderService.createCustomOrder(createOrderRequestDto);

            // Retorna 201 Created si la operación fue exitosa
            return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            if ("No hay productos elegibles para el pedido.".equals(e.getMessage())) {
                // Retorna 204 No Content si no hay productos elegibles
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            // Aquí puedes manejar otros tipos de IllegalArgumentException si los hay
        } catch (Exception e) {
            // Retorna 404 Not Found si algún ID relacionado no existe
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Retorna 500 si hay un error no esperado
    }

}
