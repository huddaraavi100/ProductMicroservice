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
       return service.getProductByIdService(id);

    }

    @GetMapping("/products")
    public List<Product> getAllProductsController(){
        return service.getALLProductsService();
    }

    @PostMapping("/add")
    public Product createProductController(@RequestBody Product product){
        return service.createProductService(product);
    }

    @PutMapping("/update/{id}")
    public Product updateProductController(@PathVariable Long id, @RequestBody Product updatedProduct){
        return service.updateProductService(id,updatedProduct);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProductController(@PathVariable Long id){
        service.deleteProductService(id);
        return "Product with id " +id+ " has been deleted";
    }
}
