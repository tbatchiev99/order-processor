package com.ibm.processor.orderapp.service;

import com.ibm.processor.orderapp.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    List<Product> getAllProducts();

    Product findById(final Integer id);

}
