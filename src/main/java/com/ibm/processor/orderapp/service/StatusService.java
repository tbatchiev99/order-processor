package com.ibm.processor.orderapp.service;

import com.ibm.processor.orderapp.entity.Status;
import org.springframework.stereotype.Service;

@Service
public interface StatusService {

    Status findByName(final String name);
}
