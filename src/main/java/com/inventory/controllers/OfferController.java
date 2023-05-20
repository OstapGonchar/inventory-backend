package com.inventory.controllers;

import com.inventory.entities.Offer;
import com.inventory.entities.Product;
import com.inventory.exceptions.BadInputException;
import com.inventory.repositories.ProductRepository;
import com.inventory.requests.OfferRequest;
import com.inventory.requests.ProductRequest;
import com.inventory.services.OfferService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@RestController
@RequestMapping("/api/offer")
public class OfferController {
    private final OfferService offerService;
    private final ProductRepository productRepository;

    private final RestTemplate restTemplate;


    @Autowired
    public OfferController(OfferService offerService, ProductRepository productRepository, RestTemplate restTemplate) {
        this.offerService = offerService;
        this.productRepository = productRepository;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/all")
    public List<Offer> offers() {
        return offerService.offers();
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
        //TODO: Sergii: Entire business logic should be in service.
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
                    .orElseThrow(() -> new BadInputException("Product not found with id: " + productRequest.getId()));

            int requestedQuantity = productRequest.getQuantity();
            int availableQuantity = product.getQuantity();
            if (requestedQuantity > availableQuantity) {
                throw new BadInputException("Quantity of Product " + product.getId() + " is not sufficient");
            }

            BigDecimal price = product.getPrice();
            BigDecimal convertedPrice = convert(product.getCurrency(), offer.getTotalCurrency(), price);
            BigDecimal productTotalAmount = convertedPrice.multiply(BigDecimal.valueOf(requestedQuantity));
            totalAmount = totalAmount.add(productTotalAmount);
            //TODO: Ostap: This is a bug, should not update products quantity.
            product.setQuantity(requestedQuantity);
            products.add(product);
        }

        offer.setProducts(products);
        offer.setTotalAmount(totalAmount);

        return offerService.addOffer(offer);
    }


   /* public Offer addOffer(@RequestBody OfferRequest offerRequest) {
        return offerService.addOffer(offerRequest);*/
   private BigDecimal convert(String fromCurrency, String toCurrency, BigDecimal amount) {
       return  offerService.convertCurrency(fromCurrency, toCurrency, amount);
   }


}
