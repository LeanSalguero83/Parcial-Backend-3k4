package com.example.ParcialSabado28.repository;

import com.example.ParcialSabado28.model.CustomerDemographic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDemographicRepository extends JpaRepository<CustomerDemographic,String> {
}
