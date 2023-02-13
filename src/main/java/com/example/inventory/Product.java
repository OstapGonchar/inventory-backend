package com.example.inventory;

public record Product(
        String id,
        String name,
        String description,
        double price,
        String currency,
        int quantity) {
}

