package com.example.ParcialSabado28.repository;

import com.example.ParcialSabado28.model.Territory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TerritoryRepository extends JpaRepository<Territory,String> {
}
