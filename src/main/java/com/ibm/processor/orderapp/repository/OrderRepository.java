package com.ibm.processor.orderapp.repository;

import com.ibm.processor.orderapp.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
