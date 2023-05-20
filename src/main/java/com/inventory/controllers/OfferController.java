package com.inventory.controllers;

import com.inventory.entities.Offer;
import com.inventory.exceptions.BadInputException;
import com.inventory.requests.OfferRequest;
import com.inventory.services.OfferService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@RestController
@RequestMapping("/api/offer")
public class OfferController {
    private final OfferService offerService;

    @Autowired
    public OfferController(OfferService offerService) {
        this.offerService = offerService;

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
       public Offer addOffer(@RequestBody OfferRequest offerRequest) throws BadInputException {
        return offerService.addNewOffer(offerRequest);}

   private BigDecimal convert(String fromCurrency, String toCurrency, BigDecimal amount) {
       return  offerService.convertCurrency(fromCurrency, toCurrency, amount);
   }


}
