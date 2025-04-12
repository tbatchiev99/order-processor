package com.ibm.processor.orderapp.repository;

import com.ibm.processor.orderapp.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
