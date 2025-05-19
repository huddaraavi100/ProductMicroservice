package com.practice.Spring.MVC.controller;

import com.practice.Spring.MVC.model.Product;
import com.practice.Spring.MVC.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService service;
    @GetMapping("/{id}")
    public Product getProductByIdController(@PathVariable Long id){
    Product p=service.getProductByIdService(id);
        return p;
    }

    @GetMapping("/product")
    public List<Product> getAllProductsController(){
        return service.getALLProductsService();
    }

    @PostMapping("/product/{id}")
    public Product createProductController(@RequestBody Product product){
        return service.createProductService(product);
    }

    @PutMapping("/product/{id}")
    public Product updateProductController(@PathVariable Long id, @RequestBody Product updatedProduct){
        return service.updateProductService(id,updatedProduct);
    }

    @DeleteMapping("/product/{id}")
    public String deleteProductController(@PathVariable Long id){
        service.deleteProductService(id);
        return "Product with id " +id+ " has been deleted";
    }
}
