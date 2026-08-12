package com.Market.MarketHub.Model;

import com.Market.MarketHub.Enum.Category;

import com.Market.MarketHub.Enum.Unit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @Column(nullable = false )
    private String brand;

    @Column(nullable = false , unique = true)
    private String sku;
    private BigDecimal purchasePrice;
    private BigDecimal sellingPrice;
    private Double quantity;
    private Unit unit;

    @Enumerated(EnumType.STRING)
    private Category category;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @PrePersist
    public void onCreate(){
        createdAt=LocalDateTime.now();
        updatedAt=LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate(){
        updatedAt=LocalDateTime.now();
    }
}
