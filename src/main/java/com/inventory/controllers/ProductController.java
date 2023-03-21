package com.inventory.controllers;

import com.inventory.entities.ProductEntity;
import com.inventory.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public List<ProductEntity> listOfProducts() {
        return productService.listOfProducts();
    }

    @PostMapping("/{id}")
    public void updateProduct(@RequestBody ProductEntity product, @PathVariable("id") Long id) {
        productService.updateProduct(product, id);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
    }

    @PutMapping()
    public void addProduct(@RequestBody ProductEntity product) {
        productService.addProduct(product);
    }

}



