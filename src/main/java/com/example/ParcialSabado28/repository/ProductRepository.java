package com.example.ParcialSabado28.repository;

import com.example.ParcialSabado28.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p WHERE p.supplier.supplierId = ?1 AND p.category.categoryId = ?2 AND (p.unitsInStock + p.unitsOnOrder) < ?3 AND p.discontinued != '1' ORDER BY (p.unitsInStock + p.unitsOnOrder) ASC")
    List<Product> findCustomProducts(Integer supplierId, Integer categoryId, Integer stockMin);
    List<Product> findBySupplierIdAndCategoryId(Integer supplierId, Integer categoryId);

}

