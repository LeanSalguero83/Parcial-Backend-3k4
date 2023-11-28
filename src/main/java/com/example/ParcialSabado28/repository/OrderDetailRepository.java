package com.example.ParcialSabado28.repository;

import com.example.ParcialSabado28.model.OrderDetail;
import com.example.ParcialSabado28.model.OrderDetailId;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailId> {
}
