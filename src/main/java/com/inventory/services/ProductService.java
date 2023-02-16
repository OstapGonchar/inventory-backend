package com.inventory.services;

import com.inventory.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    public List<Product> listOfProducts() {
        return postProducts();
    }


    public List<Product> postProducts() {
       ArrayList<Product> listOfNewProducts =  new ArrayList<Product>();
              listOfNewProducts.add(new Product("1", "boiler", "heat-water boiler", 100.0, "$", 3));
              listOfNewProducts.add(new Product("2", "tank", "softened-water tank", 20.0, "$", 2));
              listOfNewProducts.add(new Product("3", "pump", "cold water pump", 40.0, "$", 3)
        );
        return listOfNewProducts;
    }

 /*   public List<Product> addProduct(Product product) {
        product = new Product("4", "boiler", "heat-water boiler", 100.0, "$", 3);
        return postProducts().add(product);
    }*/

}






