package com.inventory.controllers;

import com.inventory.entities.Offer;
import com.inventory.entities.Product;
import com.inventory.repositories.ProductRepository;
import com.inventory.requests.OfferRequest;
import com.inventory.requests.ProductRequest;
import com.inventory.services.OfferService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class OfferControllerTest {
    @Mock
    private OfferService offerService;
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private OfferController offerController;


    @Test
    void shouldCreateOfferFromOfferRequest() {
        //given
        OfferRequest offerRequest = OfferRequest.builder()
                .name("Offer for Ostap")
                .description("Bla bla bla")
                .products(List.of(new ProductRequest(1, 3), new ProductRequest(2, 3)))
                .build();
        Product product1 = Product.builder()
                .id(1L)
                .name("P1")
                .price(BigDecimal.TEN)
                .quantity(10)
                .build();
        Product product2 = Product.builder()
                .id(2L)
                .name("P2")
                .price(BigDecimal.TEN)
                .quantity(5)
                .build();
        when(productRepository.findById(1L))
                .thenReturn(Optional.of(product1));
        when(productRepository.findById(2L))
                .thenReturn(Optional.of(product2));
        when(offerService.addOffer(any(Offer.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        //when
        Offer offer = offerController.addOffer(offerRequest);

        //then
        System.out.println(offer);
        assertEquals(BigDecimal.valueOf(60), offer.getTotalAmount());
    }

    @Disabled
    @Test
    void shouldNotCreateOfferFromOfferRequestWhenQuantityIsNotEnough() {
        //given
        OfferRequest offerRequest = OfferRequest.builder()
                .name("Offer for Ostap")
                .description("Bla bla bla")
                .products(List.of(new ProductRequest(1, 3), new ProductRequest(2, 3)))
                .build();
        Product product1 = Product.builder()
                .id(1L)
                .name("P1")
                .price(BigDecimal.TEN)
                .quantity(1)
                .build();
        Product product2 = Product.builder()
                .id(2L)
                .name("P2")
                .price(BigDecimal.TEN)
                .quantity(5)
                .build();
        when(productRepository.findById(1L))
                .thenReturn(Optional.of(product1));
        when(productRepository.findById(2L))
                .thenReturn(Optional.of(product2));
        when(offerService.addOffer(any(Offer.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        //when
        //then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> offerController.addOffer(offerRequest));
        assertEquals("Quantity of Product 1 is not sufficient", exception.getMessage());
    }
}