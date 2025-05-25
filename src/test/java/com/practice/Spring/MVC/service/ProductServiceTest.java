package com.practice.Spring.MVC.service;

import com.practice.Spring.MVC.model.Product;
import com.practice.Spring.MVC.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @Mock
    private ProductRepository repo;

    @InjectMocks
    private ProductService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetProductById_found() {
        Product product = new Product(1L, "Laptop", "Gaming laptop", 1500.0, "Electronics", 5);
        when(repo.findById(1L)).thenReturn(Optional.of(product));

        Product result = service.getProductByIdService(1L);
        assertNotNull(result);
        assertEquals("Laptop", result.getName());
    }

    @Test
    void testGetProductById_notFound() {
        when(repo.findById(1L)).thenReturn(Optional.empty());

        Product result = service.getProductByIdService(1L);
        assertNull(result);
    }

    @Test
    void testGetAllProducts() {
        List<Product> products = Arrays.asList(
                new Product(1L, "Laptop", "Gaming laptop", 1500.0, "Electronics", 5),
                new Product(2L, "Phone", "Smartphone", 800.0, "Electronics", 10)
        );
        when(repo.findAll()).thenReturn(products);

        List<Product> result = service.getAllProductsService();
        assertEquals(2, result.size());
    }

    @Test
    void testCreateProduct() {
        Product input = new Product(null, "Tablet", "Android tablet", 300.0, "Electronics", 15);
        Product saved = new Product(3L, "Tablet", "Android tablet", 300.0, "Electronics", 15);

        when(repo.save(input)).thenReturn(saved);

        Product result = service.createProductService(input);
        assertEquals("Tablet", result.getName());
        assertEquals(3L, result.getId());
    }

    @Test
    void testUpdateProduct_found() {
        Product existing = new Product(1L, "Old Laptop", "Old model", 1200.0, "Electronics", 3);
        Product update = new Product(1L, "New Laptop", "Latest model", 1700.0, "Electronics", 7);

        when(repo.findById(1L)).thenReturn(Optional.of(existing));
        when(repo.save(any(Product.class))).thenAnswer(i -> i.getArgument(0));

        Product result = service.updateProductService(1L, update);

        assertNotNull(result);
        assertEquals("New Laptop", result.getName());
        assertEquals(1700.0, result.getPrice());
        assertEquals(7, result.getQuantity());
    }

    @Test
    void testUpdateProduct_notFound() {
        Product update = new Product(1L, "New Laptop", "Latest model", 1700.0, "Electronics", 7);

        when(repo.findById(1L)).thenReturn(Optional.empty());

        Product result = service.updateProductService(1L, update);
        assertNull(result);
    }

    @Test
    void testDeleteProduct() {
        doNothing().when(repo).deleteById(1L);

        service.deleteProductService(1L);

        verify(repo, times(1)).deleteById(1L);
    }
}
