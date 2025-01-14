package com.productService.Product_Service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.productService.Product_Service.model.Product;

public interface ProductRepo extends MongoRepository<Product, String> {

}
