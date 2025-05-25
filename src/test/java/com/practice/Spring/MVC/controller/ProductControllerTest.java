package com.practice.Spring.MVC.controller;

import com.practice.Spring.MVC.model.Product;
import com.practice.Spring.MVC.response.ApiResponse;
import com.practice.Spring.MVC.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
    public void testGetProductById_Found(){
        Product product =new Product(1L,"Smart phone","Samsung galaxy",
                258.89,"Electronics",1);
        when(productService.getProductByIdService(1L)).thenReturn(Optional.of(product));
        ResponseEntity<ApiResponse<Product>> response=productController.getProductByIdController(1L);
        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        ApiResponse<Product> body=response.getBody();
        assertNotNull(body);
        assertTrue(body.isSuccess());
        assertEquals("Product found",body.getMessage());
        assertEquals(product,body.getData());
        Product result=body.getData();
    }


    @Test
    public void testGetProductById_ifNotFound(){
        when(productService.getProductByIdService(2L)).thenReturn(Optional.empty());
        ResponseEntity<ApiResponse<Product>> response=productController.getProductByIdController(2L);
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isSuccess());
        assertEquals("Product not found", response.getBody().getMessage());
        assertNull(response.getBody().getData());

    }

    @Test
    public void testGetAllProducts(){
        List<Product> products =Arrays.asList(
                new Product(1L, "Laptop", "Dell XPS", 1200.0, "Electronics", 10),
                new Product(2L, "TV", "Samsung Smart TV", 599.0, "Electronics", 3)
        );
        when(productService.getAllProductsService()).thenReturn(products);
        ResponseEntity<ApiResponse<List<Product>>> response=productController.getAllProductsController();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        ApiResponse<List<Product>> body = response.getBody();
        assertNotNull(body);
        assertTrue(body.isSuccess());
        assertEquals("All products", body.getMessage());

        List<Product> result = body.getData();
        assertEquals(2, result.size());
        assertEquals("Laptop", result.get(0).getName());
        assertEquals("TV", result.get(1).getName());

    }

    @Test
    public void testCreateProduct(){
        Product input=new Product(null, "Laptop", "MacBook Pro",
                1999.0, "Electronics", 2);
        Product created=new Product(1L,"Laptop", "MacBook Pro",
                1999.0, "Electronics", 2);

        when(productService.createProductService(input)).thenReturn(created);
        ResponseEntity<ApiResponse<Product>> response=productController.createProductController(input);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        ApiResponse<Product> body=response.getBody();
        assertNotNull(body);
        assertTrue(body.isSuccess());
        assertEquals("Product added",body.getMessage());
        assertEquals(created,body.getData());

        Product actualProduct=body.getData();
        assertNotNull(actualProduct);
        assertEquals("Laptop",actualProduct.getName());

    }


    @Test
    public void testUpdateProduct_ifExists(){
        Product input =new Product(null,"Phone","Keypad phone",
                1200.87,"Electronics",1);
        Product updated=new Product(1L,"Phone","Keypad phone",
                1200.87,"Electronics",1);
        when(productService.updateProductService(1L,input)).thenReturn(Optional.of(updated));
        ResponseEntity<ApiResponse<Product>> response=productController.updateProductController(1L,input);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        ApiResponse<Product> body = response.getBody();
        assertNotNull(body);
        assertTrue(body.isSuccess());
        assertEquals("Product updated", body.getMessage());
        assertEquals(updated, body.getData());

    }

    @Test
    public void testUpdateProduct_ifNotFound(){
        Product input =new Product(null,"Laptop","Dell",2345.98,"Electronics",1);
        when(productService.updateProductService(2L,input)).thenReturn(Optional.empty());
        ResponseEntity<ApiResponse<Product>> response=productController.updateProductController(2L,input);

        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());

        ApiResponse<Product> body=response.getBody();

        assertNotNull(body);
        assertFalse(body.isSuccess());
        assertEquals("Product not found", body.getMessage());
        assertNull(body.getData());
    }

    @Test
    public void testDeleteProduct_ifFound(){
        Product deleted =new Product(1L,"Laptop","Dell",1299.56,"Electronics",1);
        when(productService.deleteProductService(1L)).thenReturn(Optional.of(deleted));
        ResponseEntity<ApiResponse<Product>> response=productController.deleteProductController(1L);

        ApiResponse<Product> body=response.getBody();
        assertNotNull(body);
        assertTrue(body.isSuccess());
        assertEquals("Product deleted",body.getMessage());
        assertEquals(deleted,body.getData());


    }

    @Test
    public void testDeleteProduct_NotFound(){
        when(productService.deleteProductService(2L)).thenReturn(Optional.empty());
        ResponseEntity<ApiResponse<Product>> response=productController.deleteProductController(2L);
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());

        ApiResponse<Product> body=response.getBody();
        assertNotNull(body);
        assertFalse(body.isSuccess());
        assertEquals("Product not found",body.getMessage());
        assertNull(body.getData());

    }

}