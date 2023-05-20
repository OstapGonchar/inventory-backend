package com.inventory.services;

import com.inventory.entities.Offer;
import com.inventory.repositories.OfferRepository;
import com.inventory.responses.CurrencyExchangeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OfferService {

    private final OfferRepository offerRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public OfferService(OfferRepository offerRepository, RestTemplate restTemplate) {
        this.offerRepository = offerRepository;
        this.restTemplate = restTemplate;
    }


    public void updateOffer(Offer offer, Long id) {
        Offer updateOffer = offerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not exist with id: " + id));
        updateOffer.setName(offer.getName());
        updateOffer.setDescription(offer.getDescription());
        updateOffer.setClient(offer.getClient());
        updateOffer.setTotalAmount(offer.getTotalAmount());
        updateOffer.setTotalCurrency(offer.getTotalCurrency());
        updateOffer.setDiscount(offer.getDiscount());
        updateOffer.setStatus(offer.getStatus());
        offerRepository.save(offer);
    }

    public List<Offer> offers() {
        return offerRepository.findAll();
    }

    public Offer addOffer(Offer offer) {
        return offerRepository.save(offer);
    }

    public void deleteOffer(Long id) {
        if (offerRepository.findById(id).isPresent()) {
            offerRepository.deleteById(id);
        } else {
            //error
        }
    }
    public BigDecimal convertCurrency(String fromCurrency, String toCurrency, BigDecimal amount) {
        //TODO Sergii: move to separate Service
        RequestEntity<Void> requestEntity = RequestEntity.get("https://currency-converter18.p.rapidapi.com/api/v1/convert?from={fromCurrency}&to={toCurrency}&amount={amount}", fromCurrency, toCurrency, amount)
                .header("X-RapidAPI-Key", "0a83e848e8mshe0477d46cde4ac7p180993jsn813f039b57fb")
                .header("X-RapidAPI-Host", "currency-converter18.p.rapidapi.com")
                .build();
        ResponseEntity<CurrencyExchangeResponse> response = restTemplate.exchange(requestEntity, CurrencyExchangeResponse.class);
        return BigDecimal.valueOf(response.getBody().getResult().getConvertedAmount());
    }
}




