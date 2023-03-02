package com.inventory.entities;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Offer {
    private final long id;
    private final String name;
    private final String description;
    private final String client;
    private final BigDecimal totalAmount;
    private final String totalCurrency;
    private final double discount;
    private final String status;
}
