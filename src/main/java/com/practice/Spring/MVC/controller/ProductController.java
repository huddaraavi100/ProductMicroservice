package com.practice.Spring.MVC.controller;

import com.practice.Spring.MVC.model.Product;
import com.practice.Spring.MVC.response.ApiResponse;
import com.practice.Spring.MVC.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService service;
    @GetMapping("/products/{id}")
    public ResponseEntity<ApiResponse<Product>> getProductByIdController(@PathVariable Long id){
        return service.getProductByIdService(id)
                .map(product -> ResponseEntity.ok(new ApiResponse<>(true,"Product found",product)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body(new ApiResponse<>(false,"Product not found",null)));
    }

    @GetMapping("/products")
    public ResponseEntity<ApiResponse<List<Product>>> getAllProductsController(){
        List<Product> products=service.getAllProductsService();
        return ResponseEntity.ok(new ApiResponse<>(true,"All products",products));
    }

    @PostMapping("/products")
    public ResponseEntity<ApiResponse<Product>> createProductController(@RequestBody Product product){
        Product created=service.createProductService(product);
        return ResponseEntity.ok(new ApiResponse<>(true,"Product added",created));
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ApiResponse<Product>> updateProductController(@PathVariable Long id,
                                                                        @RequestBody Product product){
        return service.updateProductService(id, product)
                .map(updated -> ResponseEntity.ok(new ApiResponse<>(true,"Product updated",updated)))
                .orElseGet(() ->ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(false,"Product not found",null)));

    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<ApiResponse<Product>> deleteProductController(@PathVariable Long id) {
        return service.deleteProductService(id)
                .map(deleted -> ResponseEntity.ok(new ApiResponse<>(true, "Product deleted", deleted)))
                .orElseGet(() ->ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(false, "Product not found", null)));
    }



}
