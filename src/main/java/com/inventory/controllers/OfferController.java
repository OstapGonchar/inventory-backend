package com.inventory.controllers;

import com.inventory.entities.Offer;
import com.inventory.services.OfferService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/put")
    public void addOffer(@RequestBody Offer offer) {
        offerService.addOffer(offer);
    }

    @PostMapping("/change/{id}")
    public void updateOffer(@PathVariable long id, @RequestBody Offer offerDetails) {
        offerService.updateOffer(offerDetails, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOffer(@PathVariable long id) {
        offerService.deleteOffer(id);
    }

}
