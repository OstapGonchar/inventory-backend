package com.inventory.services;

import com.inventory.entities.Offer;
import com.inventory.repositories.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferService {

    private final OfferRepository offerRepository;

    @Autowired
    public OfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
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

    public void addOffer(Offer offer) {
        offerRepository.save(offer);
    }

    public void deleteOffer(long id) {
        offerRepository.deleteById(id);
    }





  /*  public void addOffer(Offer offer) {
        offerRepository.
    }

    public void create(Offer offer, String id) {
        offer.delete(id);
        addOffer(offer);
    }*/


    /*    public ResponseEntity<Employee> updateEmployee(@PathVariable long id,@RequestBody Employee employeeDetails) {
            Employee updateEmployee = employeeRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));

            updateEmployee.setFirstName(employeeDetails.getFirstName());
            updateEmployee.setLastName(employeeDetails.getLastName());
            updateEmployee.setEmailId(employeeDetails.getEmailId());

            employeeRepository.save(updateEmployee);

            return ResponseEntity.ok(updateEmployee);*/

    }



