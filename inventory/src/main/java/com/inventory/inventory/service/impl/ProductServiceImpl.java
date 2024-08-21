package com.inventory.inventory.service.impl;

import com.inventory.inventory.entity.Product;
import com.inventory.inventory.exception.ResourceNotFoundException;
import com.inventory.inventory.repository.ProductRepository;
import com.inventory.inventory.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {


    private final ProductRepository productRepository;

    @Override
    public List<Product> findAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long productId) {
        Product foundedProduct = productRepository.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException("Product","id", productId.toString())
        );

        return foundedProduct;
    }

    @Override
    public Product findProductByName(String productName) {
        Product foundedProduct = productRepository.findByName(productName).orElseThrow(
                () -> new ResourceNotFoundException("Product","name", productName)
        );
        return foundedProduct;
    }

    @Override
    public Product save(Product product) {
        Optional<Product> foundedProduct = productRepository.findByName(product.getName());
        if (foundedProduct.isPresent()) {
            Product productUpdated = foundedProduct.get();
            productUpdated.setQuantity(productUpdated.getQuantity() + product.getQuantity());
            Product saveProductUpdated = productRepository.save(productUpdated);
            return saveProductUpdated;

        }{
            Product newProduct = productRepository.save(product);
            return newProduct;

        }
    }

    @Override
    public boolean delete(Long productId) {
        Product foundedProduct = productRepository.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException("Product","id", productId.toString())
        );

        if (foundedProduct != null) {
            productRepository.deleteById(productId);
            return true;
        }
        return false;
    }

    @Override
    public Product update(Product product) {
        Product foundedProduct = productRepository.findById(product.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Product","id", product.getId().toString())
        );

        foundedProduct.setName(product.getName());
        foundedProduct.setQuantity(product.getQuantity());
        foundedProduct.setProductType(product.getProductType());

        Product updatedProduct = productRepository.save(foundedProduct);
        return updatedProduct;

    }
}
