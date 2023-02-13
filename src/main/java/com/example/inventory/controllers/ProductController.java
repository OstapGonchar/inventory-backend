package com.example.inventory.controllers;

import com.example.inventory.Product;
import com.example.inventory.services.ProductService;
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

    @PostMapping("/post")
    public List<Product> postProducts() {
        return productService.postProducts();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable("id") String id) {
        listOfProducts().remove(new String((id)));
    }

  /*  @DeleteMapping("/delete/{id}")
    public boolean deleteProduct(@PathVariable("id") String id) {
       return listOfProducts().remove(new String((id)));
    }
*/
    @PutMapping("/add/{id}")
    public void updateProduct(@PathVariable("id") String id) {
        listOfProducts().add(new Product("4", "valve", "ball-valve", 10.0, "$", 4));
    }

}



