package com.inventory.inventory.controller;

import com.inventory.inventory.constant.ProductConstants;
import com.inventory.inventory.dto.Response;
import com.inventory.inventory.entity.Product;
import com.inventory.inventory.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final IProductService productService;

    @GetMapping
    public ResponseEntity<Response> getAllProducts() {
        List<Product> allProduct = productService.findAllProduct();
        return ResponseEntity.status(HttpStatus.OK).body(new Response(ProductConstants.STATUS_200,"success get data",allProduct));
    }

    @PostMapping
    public ResponseEntity<Response> createProduct(@RequestBody Product product) {
        Product newProduct = productService.save(product);
        return ResponseEntity.status(HttpStatus.OK).body(new Response(ProductConstants.STATUS_200,"success get data",newProduct));

    }
}
