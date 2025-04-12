package com.ibm.processor.orderapp.service.impl;

import com.ibm.processor.orderapp.entity.Product;
import com.ibm.processor.orderapp.exception.NotFoundException;
import com.ibm.processor.orderapp.repository.ProductRepository;
import com.ibm.processor.orderapp.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


    @Override
    public Product findById(Integer id) {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("No product with id %d found.", id)));
    }
}
