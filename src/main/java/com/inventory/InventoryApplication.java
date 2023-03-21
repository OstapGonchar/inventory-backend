
package com.inventory;

import com.inventory.entities.Offer;
import com.inventory.entities.Product;
import com.inventory.repositories.OfferRepository;
import com.inventory.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class InventoryApplication implements CommandLineRunner {

    @Autowired
    private OfferRepository offerRepository;
    @Autowired
    private ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(InventoryApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (offerRepository.findByClient("Ostap").isEmpty()) {
            Offer offer = Offer.builder()
                    .name("Offer for Ostap")
                    .description("Bla bla bla")
                    .client("Ostap")
                    .build();
            offerRepository.save(offer);
        }
        if (productRepository.findAll().isEmpty()) {
            productRepository.save(new Product(1L, "boiler", "heat-water boiler", BigDecimal.valueOf(100.0), "USD", 3));
            productRepository.save(new Product(2L, "tank", "softened-water tank", BigDecimal.valueOf(20.0), "USD", 2));
            productRepository.save(new Product(3L, "pump", "cold water pump", BigDecimal.valueOf(40.0), "USD", 3));
        }
    }
}

