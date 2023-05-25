package com.inventory.services;

import com.inventory.entities.Product;
import com.inventory.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> listOfProducts() {
        return productRepository.findAll();
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        if (productRepository.findById(id).isPresent()) {
            productRepository.deleteById(id);
        } else {
            //error
        }
    }

    public void updateProduct(Product product, Long id) {
        productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not exist with id: " + id));
        productRepository.save(product);
    }
}







