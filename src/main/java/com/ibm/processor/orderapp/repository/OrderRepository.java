package com.ibm.processor.orderapp.repository;

import com.ibm.processor.orderapp.dto.OrderDto;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OrderDto, Integer> {
}
