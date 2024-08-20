package com.inventory.inventory.service;

import com.inventory.inventory.exception.Product;

import java.util.List;

public interface IProductService {

    List<Product> findAllProduct();
    Product findById(Long productId);
    Product findProductByName(String productName);
    void save(Product product);
    boolean delete(Long productId);

}
