
package com.inventory;

import com.inventory.entities.Offer;
import com.inventory.entities.Product;
import com.inventory.repositories.OfferRepository;
import com.inventory.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

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
        if (productRepository.findAll().isEmpty()) {
            productRepository.saveAll(List.of(
                    Product.builder()
                            .name("boiler")
                            .description("heat-water boiler")
                            .price(BigDecimal.valueOf(100.0))
                            .currency("USD")
                            .quantity(3)
                            .build(),
                    Product.builder()
                            .name("tank")
                            .description("softened-water tank")
                            .price(BigDecimal.valueOf(20.0))
                            .currency("USD")
                            .quantity(2)
                            .build(),
                    Product.builder()
                            .name("pump")
                            .description("cold water pump")
                            .price(BigDecimal.valueOf(40.0))
                            .currency("USD")
                            .quantity(3)
                            .build()
                    )
            );
        }
        if (offerRepository.findByClient("Ostap").isEmpty()) {
            Offer offer = Offer.builder()
                    .name("Offer for Ostap")
                    .description("Bla bla bla")
                    .client("Ostap")
                    .totalAmount(BigDecimal.ZERO)
                    .totalCurrency("USD")
                    .discount(0.2)
                    .products(List.of(productRepository.findByName("boiler")))
                    .build();
            offerRepository.save(offer);
        }

        System.out.println(offerRepository.findAll());
        System.out.println(productRepository.findAll());
    }

}

