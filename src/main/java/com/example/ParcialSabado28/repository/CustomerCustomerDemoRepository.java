package com.example.ParcialSabado28.repository;

import com.example.ParcialSabado28.model.CustomerCustomerDemo;
import com.example.ParcialSabado28.model.CustomerCustomerDemoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerCustomerDemoRepository  extends JpaRepository<CustomerCustomerDemo, CustomerCustomerDemoId> {
}
