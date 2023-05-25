package com.inventory.services;

import com.inventory.entities.Offer;
import com.inventory.entities.Product;
import com.inventory.exceptions.BadInputException;
import com.inventory.repositories.OfferRepository;
import com.inventory.repositories.ProductRepository;
import com.inventory.requests.OfferRequest;
import com.inventory.requests.ProductRequest;
import com.inventory.responses.CurrencyExchangeResponse;
import com.inventory.responses.CurrencyExchangeResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OfferServiceTest {
    @Mock
    private OfferRepository offerRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private OfferService offerService;


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
        CurrencyExchangeResponse currencyExchangeResponse = CurrencyExchangeResponse.builder()
                .result(CurrencyExchangeResult.builder()
                        .convertedAmount(0.0)
                        .build())
                .build();
        when(restTemplate.exchange(any(), eq(CurrencyExchangeResponse.class)))
                .thenReturn(new ResponseEntity<>(currencyExchangeResponse, HttpStatusCode.valueOf(200)));
        when(offerRepository.save(any(Offer.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        //when
        Offer offer = offerService.addNewOffer(offerRequest);

        //then
        assertEquals(BigDecimal.valueOf(0.0), offer.getTotalAmount());
    }


    @Test
    void shouldNotCreateOfferFromOfferRequestWhenQuantityIsNotEnough() throws RuntimeException {
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
                .quantity(4)
                .build();
        Product product2 = Product.builder()
                .id(2L)
                .name("P2")
                .price(BigDecimal.TEN)
                .quantity(1)
                .build();
        when(productRepository.findById(1L))
                .thenReturn(Optional.of(product1));
        when(productRepository.findById(2L))
                .thenReturn(Optional.of(product2));
        CurrencyExchangeResponse currencyExchangeResponse = CurrencyExchangeResponse.builder()
                .result(CurrencyExchangeResult.builder()
                        .convertedAmount(0.0)
                        .build())
                .build();
        when(restTemplate.exchange(any(), eq(CurrencyExchangeResponse.class)))
                .thenReturn(new ResponseEntity<>(currencyExchangeResponse, HttpStatusCode.valueOf(200)));


        //when
        //then
        RuntimeException exception = assertThrows(BadInputException.class, () -> offerService.addNewOffer(offerRequest));
        assertEquals("Quantity of Product 2 is not sufficient", exception.getMessage());
        verify(productRepository, times(2)).findById(anyLong());
    }

}