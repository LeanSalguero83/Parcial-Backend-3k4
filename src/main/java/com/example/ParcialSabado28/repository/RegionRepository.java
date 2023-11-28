package com.example.ParcialSabado28.repository;

import com.example.ParcialSabado28.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region,Integer> {

}
