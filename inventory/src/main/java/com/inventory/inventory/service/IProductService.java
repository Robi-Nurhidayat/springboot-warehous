package com.inventory.inventory.service;

import com.inventory.inventory.entity.Product;

import java.util.List;

public interface IProductService {

    List<Product> findAllProduct();
    Product findById(Long productId);
    Product findProductByName(String productName);
    Product save(Product product);
    boolean delete(Long productId);
    Product update(Product product);


}
