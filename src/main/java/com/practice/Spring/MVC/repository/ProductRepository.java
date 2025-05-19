package com.practice.Spring.MVC.repository;

import com.practice.Spring.MVC.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    // Custom method to get products with price > 2000
    List<Product> findByPriceGreaterThan(double price);
}
