package com.inventory.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "offers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String description;
    @Column(nullable = false)
    private String client;
    @Column(nullable = false)
    private BigDecimal totalAmount;
    @Column(nullable = false)
    private String totalCurrency;
    @Column(nullable = false)
    private double discount;
    private String status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "offer_product",
            joinColumns = @JoinColumn(name = "offer_id"),
            inverseJoinColumns = @JoinColumn(
                    name = "product_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(
                            name = "fk_offer_products_product_id",
                            foreignKeyDefinition = "FOREIGN KEY (product_id) REFERENCES product (id) ON DELETE RESTRICT)"
                    )
            )
    )
    private List<Product> products = new ArrayList<>();
}