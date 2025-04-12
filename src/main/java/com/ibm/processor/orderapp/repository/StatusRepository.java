package com.ibm.processor.orderapp.repository;

import com.ibm.processor.orderapp.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatusRepository extends JpaRepository<Status, Integer> {

    Optional<Status>  findByName(final String name);

}
