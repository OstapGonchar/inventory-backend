
package com.inventory;

import com.inventory.entities.Offer;
import com.inventory.repositories.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InventoryApplication implements CommandLineRunner {

	@Autowired
	private OfferRepository offerRepository;

	public static void main(String[] args) {
		SpringApplication.run(InventoryApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Offer offer = Offer.builder()
				.name("Offer for Ostap")
				.description("Bla bla bla")
				.client("Ostap")
				.build();
		offerRepository.save(offer);
	}
}

