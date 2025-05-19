package com.practice.Spring.MVC.controller;

import com.practice.Spring.MVC.model.Product;
import com.practice.Spring.MVC.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetProductById() {
        Product product = new Product(1L, "Laptop", "Gaming laptop",
                1500.0, "Electronics", 5);
        when(productService.getProductByIdService(2L)).thenReturn(product);

        Product result = productController.getProductByIdController(2L);
        assertNotNull(result);
        assertEquals("Laptop", result.getName());
        assertEquals("Gaming laptop", result.getDescription());
        assertEquals(1500.0, result.getPrice());
        assertEquals("Electronics", result.getCategory());
        assertEquals(5, result.getQuantity());
    }

    @Test
    void testGetAllProducts() {
        List<Product> productList = Arrays.asList(
                new Product(1L, "Laptop", "Gaming laptop", 1500.0, "Electronics", 5),
                new Product(2L, "Phone", "Smartphone", 800.0, "Electronics", 10)
        );

        when(productService.getALLProductsService()).thenReturn(productList);

        List<Product> result = productController.getAllProductsController();
        assertEquals(2, result.size());
        assertEquals("Phone", result.get(1).getName());
    }

    @Test
    void testCreateProduct() {
        Product input = new Product(null, "Tablet", "Android tablet", 300.0, "Electronics", 15);
        Product saved = new Product(3L, "Tablet", "Android tablet", 300.0, "Electronics", 15);

        when(productService.createProductService(input)).thenReturn(saved);

        Product result = productController.createProductController(input);
        assertEquals(3L, result.getId());
        assertEquals("Tablet", result.getName());
    }

    @Test
    void testUpdateProduct() {
        Product updated = new Product(1L, "Monitor", "24-inch HD monitor",
                200.0, "Electronics", 8);
        when(productService.updateProductService(1L, updated)).thenReturn(updated);

        Product result = productController.updateProductController(1L, updated);
        assertEquals("Monitor", result.getName());
        assertEquals(200.0, result.getPrice());
    }

    @Test
    void testDeleteProduct() {
        doNothing().when(productService).deleteProductService(1L);

        String result = productController.deleteProductController(1L);
        assertEquals("Product with id 1 has been deleted", result);
    }
}
