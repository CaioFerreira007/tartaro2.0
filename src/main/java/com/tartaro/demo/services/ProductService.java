package com.tartaro.demo.services;

import com.tartaro.demo.entities.Product;
import com.tartaro.demo.repositories.ProductRepository;
import com.tartaro.demo.services.middlewares.DataBaseException;
import com.tartaro.demo.services.middlewares.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {


    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product findById(Long id){
        Optional<Product> optional = productRepository.findById(id);
        return optional.orElseThrow(()-> new ResourceNotFoundException(id));
    }

    public Product insert(Product product){
        return  productRepository.save(product);
    }

    public void delete(Long id){
        try{
            Product p = productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id));
            productRepository.delete(p);

        }catch (ResourceNotFoundException e){
            throw new DataBaseException(e.getMessage());
        }
    }

    public Product update(Product product, Long id){
        try{
            Product p =  productRepository.getReferenceById(id);
            updateData(p,product);
            return productRepository.save(p);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    public void updateData(Product p1, Product p2){
        p1.setName(p2.getName());
        p1.setCategory(p2.getCategory());
        p1.setPrice(p2.getPrice());
        p1.setDescription(p2.getDescription());
    }


}
