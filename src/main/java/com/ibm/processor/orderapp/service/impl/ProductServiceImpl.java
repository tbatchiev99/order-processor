package com.ibm.processor.orderapp.service.impl;

import com.ibm.processor.orderapp.dto.ProductDto;
import com.ibm.processor.orderapp.entity.Product;
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
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    private ProductDto toDto(final Product product) {

        final ProductDto productDto = new ProductDto();

        productDto.setId(product.getId());
        productDto.setName(product.getName());

        return productDto;
    }
}
