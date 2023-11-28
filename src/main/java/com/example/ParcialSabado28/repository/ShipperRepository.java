package com.example.ParcialSabado28.repository;

import com.example.ParcialSabado28.model.Shipper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipperRepository extends JpaRepository<Shipper,Integer> {
}
