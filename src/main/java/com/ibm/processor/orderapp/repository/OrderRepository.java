package com.ibm.processor.orderapp.repository;

import com.ibm.processor.orderapp.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Integer> {


    @Query(value = "SELECT nextval('order_nr_seq')", nativeQuery = true)
    Long getNextOrderNr();

}
