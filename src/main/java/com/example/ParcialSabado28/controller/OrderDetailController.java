package com.example.ParcialSabado28.controller;

import com.example.ParcialSabado28.model.OrderDetail;
import com.example.ParcialSabado28.model.OrderDetailId;
import com.example.ParcialSabado28.services.OrderDetailService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;



@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/orderDetails")
public class OrderDetailController {

    OrderDetailService orderDetailService;

    @GetMapping
    public ResponseEntity<List<OrderDetailDto>> findAll() {
        List<OrderDetailDto> details = orderDetailService.findAll();
        return new ResponseEntity<>(details, HttpStatus.OK);
    }

    @GetMapping("/{orderId}/{productId}")
    public ResponseEntity<OrderDetailDto> findById(@PathVariable int orderId, @PathVariable int productId) {
        OrderDetailId id = new OrderDetailId(orderId, productId);
        Optional<OrderDetailDto> detail = orderDetailService.findById(id);

        return detail.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<OrderDetailDto> save(@RequestBody OrderDetailDto detailDto) {
        OrderDetailDto savedDetail = orderDetailService.save(detailDto);
        return new ResponseEntity<>(savedDetail, HttpStatus.CREATED);
    }

    @PutMapping("/{orderId}/{productId}")
    public ResponseEntity<OrderDetailDto> update(@PathVariable int orderId, @PathVariable int productId,
                                                 @RequestBody OrderDetailDto updatedDetail) {
        OrderDetailId id = new OrderDetailId(orderId, productId);

        try {
            OrderDetailDto updatedDetailDto = orderDetailService.update(id, updatedDetail);
            return new ResponseEntity<>(updatedDetailDto, HttpStatus.ACCEPTED);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{orderId}/{productId}")
    public ResponseEntity<Void> delete(@PathVariable int orderId, @PathVariable int productId) {
        OrderDetailId id = new OrderDetailId(orderId, productId);

        try {
            orderDetailService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}