package com.ibm.processor.orderapp.repository;

import com.ibm.processor.orderapp.entity.Order;
import com.ibm.processor.orderapp.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
