package com.ibm.processor.orderapp.service;

import com.ibm.processor.orderapp.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    List<ProductDto> getAllProducts();

}
