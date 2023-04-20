package com.inventory.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/*

{
  name: "Request for Ostap",
  description: "desc",
  client: "Ostap",
  currency: "EUR",
  products: [
    {
        id: 1,
        quantity: 2
    },
    {
        id: 3,
        quantity: 1
    }
  ]
}

 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OfferRequest {
    private String name;
    private String description;
    private String client;
    private String currency;
    private List<ProductRequest> products;
}
