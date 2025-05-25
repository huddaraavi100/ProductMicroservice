package com.practice.Spring.MVC.service;

import com.practice.Spring.MVC.model.Product;
import com.practice.Spring.MVC.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductService implements ServiceInterface {
    @Autowired
    private ProductRepository repo;

    public List<Product> getExpensiveProducts(){
        return repo.findByPriceGreaterThan(2000);
    }

   public Optional<Product> getProductByIdService(Long id){

        return repo.findById(id);
   }

   public List<Product> getAllProductsService(){
       return repo.findAll();
   }

   public Product createProductService(Product product){
       return repo.save(product);
   }

   public Optional<Product> updateProductService(Long id, Product newproduct){
      return repo.findById(id).map(updated -> {
          updated.setName(newproduct.getName());
          updated.setPrice(newproduct.getPrice());
          updated.setQuantity(newproduct.getQuantity());
          updated.setDescription(newproduct.getDescription());
          updated.setCategory(newproduct.getCategory());
         return repo.save(updated);

      });
   }

   public Optional<Product>  deleteProductService(Long id){

        return repo.findById(id).map(product ->{
            repo.deleteById(id);
            return product;
        });
   }

}
