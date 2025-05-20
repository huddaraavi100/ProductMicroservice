package com.practice.Spring.MVC.service;

import com.practice.Spring.MVC.model.Product;
import com.practice.Spring.MVC.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductService implements ServiceInterface {
    @Autowired
    private ProductRepository repo;

    public List<Product> getExpensiveProducts(){
        return repo.findByPriceGreaterThan(2000);
    }

   public Product getProductByIdService(Long id){
       return repo.findById(id).orElse(null);
   }

   public List<Product> getALLProductsService(){
       return repo.findAll();
   }

   public Product createProductService(Product product){
       return repo.save(product);
   }

   public Product updateProductService(Long id, Product updatedproduct){
       Product product = repo.findById(id).orElse(null);
       if(product!=null){
           product.setCategory(updatedproduct.getCategory());
           product.setDescription((updatedproduct.getDescription()));
           product.setName(updatedproduct.getName());
           product.setPrice(updatedproduct.getPrice());
           product.setQuantity(updatedproduct.getQuantity());
           return repo.save(product);
       }
       return null;
   }

   public void deleteProductService(Long id){
       repo.deleteById(id);
   }

}
