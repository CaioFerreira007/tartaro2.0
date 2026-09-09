package com.tartaro.demo.resources;

import com.tartaro.demo.entities.Category;
import com.tartaro.demo.entities.Product;
import com.tartaro.demo.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResources {
    private final CategoryService categoryService;

    public CategoryResources(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        List<Category> categories = categoryService.findAll();
        return ResponseEntity.ok().body(categories);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> findById(@PathVariable(value = "id") Long id) {
        Category category = categoryService.findById(id);
        return ResponseEntity.ok().body(category);
    }

    @PostMapping
    public ResponseEntity<Category> insert(@RequestBody Category category) {
        Category c = categoryService.insert(category);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(c.getId()).toUri();

        return ResponseEntity.created(uri).body(c);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Category> delete(@PathVariable(value = "id") Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Category> update(@PathVariable(value = "id") Long id, @RequestBody Category category) {
        Category c = categoryService.update(category, id);
        return ResponseEntity.ok().body(c);
    }

}
