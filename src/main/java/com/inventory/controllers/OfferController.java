package com.inventory.controllers;

import com.inventory.entities.Offer;
import com.inventory.repositories.ProductRepository;
import com.inventory.requests.OfferRequest;
import com.inventory.services.OfferService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
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
        return offerService.addNewOffer(offerRequest);}

   private BigDecimal convert(String fromCurrency, String toCurrency, BigDecimal amount) {
       return  offerService.convertCurrency(fromCurrency, toCurrency, amount);
   }


}
