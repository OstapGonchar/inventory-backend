package com.inventory.services;

import com.inventory.entities.Product;
import com.inventory.entities.ProductEntity;
import com.inventory.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final List<Product> products = new ArrayList<>();

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
        products.add(new Product("1", "boiler", "heat-water boiler", 100.0, "USD", 3));
        products.add(new Product("2", "tank", "softened-water tank", 20.0, "USD", 2));
        products.add(new Product("3", "pump", "cold water pump", 40.0, "USD", 3));
    }

    public List<ProductEntity> listOfProducts() {
        return productRepository.findAll();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void deleteProduct(String id) {
        int position = 0;
        boolean positionFound = false;
        for (int i = 0; i < products.size(); i++) {
            if (id.equals(products.get(i).id())) {
                positionFound = true;
                position = i;
                break;
            }
        }
        if (positionFound) {
            products.remove(position);
        } else  {
            //TODO: Add error 401
        }
    }

    public void updateProduct(Product product, String id) {
        deleteProduct(id);
        addProduct(product);
    }

}






