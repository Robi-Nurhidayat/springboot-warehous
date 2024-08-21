package com.inventory.inventory.controller;

import com.inventory.inventory.constant.ProductConstants;
import com.inventory.inventory.dto.ErrorResponseDto;
import com.inventory.inventory.dto.Response;
import com.inventory.inventory.entity.Product;
import com.inventory.inventory.service.IProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "CRUD REST APIs for product",
        description = "CRUD REST APIs in product to CREATE, FIND, UPDATE AND DELETE Users"
)

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final IProductService productService;


    @Operation(
            summary = "Get all products REST API",
            description = "REST API to Get all products"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping
    public ResponseEntity<Response> getAllProducts() {
        List<Product> allProduct = productService.findAllProduct();
        return ResponseEntity.status(HttpStatus.OK).body(new Response(ProductConstants.STATUS_200,ProductConstants.MESSAGE_200,allProduct));
    }

    @Operation(
            summary = "find products REST API",
            description = "REST API to find product by name"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )

    @GetMapping("/search")
    public ResponseEntity<Response> findProductByName(@RequestParam("name") String name){
        Product productByName = productService.findProductByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(new Response(ProductConstants.STATUS_200,ProductConstants.MESSAGE_200,productByName));
    }


    @Operation(
            summary = "Create products REST API",
            description = "REST API to create new products"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PostMapping
    public ResponseEntity<Response> createProduct(@RequestBody Product product) {
        Product newProduct = productService.save(product);
        return ResponseEntity.status(HttpStatus.OK).body(new Response(ProductConstants.STATUS_201,ProductConstants.MESSAGE_201,newProduct));

    }


    @Operation(
            summary = "update products REST API",
            description = "REST API to update products"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PutMapping
    public ResponseEntity<Response> updateProduct(@RequestBody Product product) {
        Product updatedProduct = productService.update(product);
        return ResponseEntity.status(HttpStatus.OK).body(new Response(ProductConstants.STATUS_200,ProductConstants.MESSAGE_200,updatedProduct));

    }


    @Operation(
            summary = "Delete products REST API",
            description = "REST API to delete products by id"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @DeleteMapping
    public ResponseEntity<Response> deleteProduct(@RequestParam("productId") Long productId) {

        boolean isDeleted = productService.delete(productId);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body(new Response(ProductConstants.STATUS_200,ProductConstants.MESSAGE_200,""));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Response(ProductConstants.STATUS_417,ProductConstants.MESSAGE_417_DELETE,""));


    }
}
