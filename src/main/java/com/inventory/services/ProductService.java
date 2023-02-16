package com.inventory.services;

import com.inventory.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private final List<Product> products = new ArrayList<>();

    public ProductService() {
        products.add(new Product("1", "boiler", "heat-water boiler", 100.0, "$", 3));
        products.add(new Product("2", "tank", "softened-water tank", 20.0, "$", 2));
        products.add(new Product("3", "pump", "cold water pump", 40.0, "$", 3));
    }

    public List<Product> listOfProducts() {
        return products;
    }

    public List<Product> postProducts() {
        ArrayList<Product> listOfNewProducts = new ArrayList<Product>();
        listOfNewProducts.add(new Product("1", "boiler", "heat-water boiler", 100.0, "$", 3));
        listOfNewProducts.add(new Product("2", "tank", "softened-water tank", 20.0, "$", 2));
        listOfNewProducts.add(new Product("3", "pump", "cold water pump", 40.0, "$", 3)
        );
        return listOfNewProducts;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void deleteProduct(String id) {
        int position = 0;
        boolean positionFound = false;
       // Optional<Product> optionalProduct = products.stream().filter(product -> product.id().equals(id)).findFirst();
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

 /*   public List<Product> addProduct(Product product) {
        product = new Product("4", "boiler", "heat-water boiler", 100.0, "$", 3);
        return postProducts().add(product);
    }*/

}






