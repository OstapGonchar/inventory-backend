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
        int position = -1;
        for (int i = 0; i < listOfProducts().size(); i++) {
            if (id == listOfProducts().get(i).id()) {
                position = i;
                break;
            }
        }
        if (position != -1) {
            productRepository.deleteById(id);
        } else {
            //TODO: Add error 401
        }
    }

    public void updateProduct(Product product, Long id) {
        Product updateProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not exist with id: " + id));
        updateProduct.setName(product.getName());
        updateProduct.setDescription(product.getDescription());
        updateProduct.setAmount(product.getAmount());
        updateProduct.setCurrency(product.getCurrency());
        updateProduct.setQuantity(product.getQuantity());
        productRepository.save(product);

    }
}







