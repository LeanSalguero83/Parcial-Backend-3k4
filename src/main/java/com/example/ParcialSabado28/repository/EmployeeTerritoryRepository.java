package com.example.ParcialSabado28.repository;

import com.example.ParcialSabado28.model.EmployeeTerritory;
import com.example.ParcialSabado28.model.EmployeeTerritoryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeTerritoryRepository  extends JpaRepository<EmployeeTerritory, EmployeeTerritoryId> {
}
