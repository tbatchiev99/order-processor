package com.ibm.processor.orderapp.repository;

import com.ibm.processor.orderapp.entity.Order;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Order, Integer> {
}
