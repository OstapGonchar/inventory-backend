package com.inventory.repositories;

import com.inventory.entities.Offer;
import org.springframework.data.repository.CrudRepository;

public interface OfferRepository extends CrudRepository<Offer, Long> {
}
