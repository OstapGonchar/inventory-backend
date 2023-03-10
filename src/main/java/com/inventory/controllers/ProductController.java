package com.inventory.controllers;

import com.inventory.Product;
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
    public List<Product> listOfProducts() {
        return productService.listOfProducts();
    }

    @PostMapping("/{id}")
    public void updateProduct(@RequestBody Product product, @PathVariable("id") String id) {
        productService.updateProduct(product, id);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") String id) {
        productService.deleteProduct(id);
    }

    @PutMapping()
    public void addProduct(@RequestBody Product product) {
        productService.addProduct(product);
    }

}



