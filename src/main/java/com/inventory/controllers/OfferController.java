package com.inventory.controllers;

import com.inventory.entities.Offer;
import com.inventory.entities.Product;
import com.inventory.repositories.OfferRepository;
import com.inventory.repositories.ProductRepository;
import com.inventory.requests.OfferRequest;
import com.inventory.requests.ProductRequest;
import com.inventory.services.OfferService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@RestController
@RequestMapping("/api/offer")
public class OfferController {
    private final OfferService offerService;
    private final ProductRepository productRepository;

    @Autowired
    public OfferController(OfferService offerService, ProductRepository productRepository) {
        this.offerService = offerService;
        this.productRepository = productRepository;
    }

    @GetMapping("/all")
    public List<Offer> offers() {
        return offerService.offers();
    }

    @PutMapping()
    public void addOffer(@RequestBody Offer offer) {
        offerService.addOffer(offer);
    }

    @PostMapping("/{id}")
    public void updateOffer(@PathVariable long id, @RequestBody Offer offerDetails) {
        offerService.updateOffer(offerDetails, id);
    }

    @DeleteMapping("/{id}")
    public void deleteOffer(@PathVariable long id) {
        offerService.deleteOffer(id);
    }

    @PostMapping()
    public Offer addOffer(@RequestBody OfferRequest offerRequest) {
        Offer offer = Offer.builder()
                .name(offerRequest.getName())
                .description(offerRequest.getDescription())
                .client(offerRequest.getClient())
                .totalCurrency(offerRequest.getCurrency())
                .build();

        List<Product> products = new ArrayList<>();
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (ProductRequest productRequest : offerRequest.getProducts()) {
            Product product = productRepository.findById(productRequest.getId())
                    .orElseThrow(() -> new RuntimeException("Product not found with id: " + productRequest.getId()));
            products.add(product);

            int quantity = productRequest.getQuantity();
            BigDecimal price = product.getPrice();
            BigDecimal productTotalAmount = price.multiply(BigDecimal.valueOf(quantity));
            totalAmount = totalAmount.add(productTotalAmount);
        }

        offer.setProducts(products);
        offer.setTotalAmount(totalAmount);

        return offerService.addOffer(offer);
    }

}
