package com.inventory.repositories;

import com.inventory.entities.Offer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OfferRepository extends CrudRepository<Offer, Long> {

    List<Offer> findAll();

    List<Offer> findByName(String name);

    List<Offer> findByClient(String client);
}
