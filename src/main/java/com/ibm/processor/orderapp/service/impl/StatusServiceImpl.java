package com.ibm.processor.orderapp.service.impl;

import com.ibm.processor.orderapp.entity.Status;
import com.ibm.processor.orderapp.repository.StatusRepository;
import com.ibm.processor.orderapp.service.StatusService;
import org.springframework.stereotype.Service;

@Service
public class StatusServiceImpl implements StatusService {

    private final StatusRepository statusRepository;

    public StatusServiceImpl(final StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    public Status findByName(final String name) {
        return statusRepository.findByName(name).orElseThrow(() -> new RuntimeException(String.format("No status with name %s found.", name)));
    }
}
