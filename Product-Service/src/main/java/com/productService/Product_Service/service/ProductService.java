package com.productService.Product_Service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.productService.Product_Service.dto.ProductResponse;
import com.productService.Product_Service.dto.ProductRequest;
import com.productService.Product_Service.model.Product;
import com.productService.Product_Service.repository.ProductRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepo productRepo;

    public ProductResponse createProduct(ProductRequest productRequest){
    
        Product product = Product.builder()
            .name(productRequest.name())
            .price(productRequest.price())
            .description(productRequest.description())
            .build();
        productRepo.save(product);
        log.info("Product created successfully");
    
        return new ProductResponse(product.getId(),product.getName(),product.getDescription(),product.getPrice());
    }

    public List<ProductResponse> getAllProducts(){
        return productRepo.findAll()
                .stream()
                .map(product -> new ProductResponse(product.getId(),product.getName(),product.getDescription(),product.getPrice()))
                .toList();
    }
}
