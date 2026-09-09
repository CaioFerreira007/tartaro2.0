package com.tartaro.demo.resources;

import com.tartaro.demo.entities.Product;
import com.tartaro.demo.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductResources {

    private final ProductService productService;

    public ProductResources(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        List<Product> products = productService.findAll();
        return ResponseEntity.ok().body(products);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> findById(@PathVariable(value = "id") Long id) {
        Product product = productService.findById(id);
        return ResponseEntity.ok().body(product);
    }
    @PostMapping
    public ResponseEntity<Product> insert(@RequestBody Product product) {
        Product newProduct = productService.insert(product);
        URI uri =  ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newProduct.getId()).toUri();
        return ResponseEntity.created(uri).body(newProduct);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Product> delete(@PathVariable(value = "id") Long id) {
         productService.delete(id);
         return ResponseEntity.noContent().build();
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Product> update(@PathVariable(value = "id") Long id, @RequestBody Product product) {
        Product p = productService.update(product, id);
        return ResponseEntity.ok().body(p);
    }
}
