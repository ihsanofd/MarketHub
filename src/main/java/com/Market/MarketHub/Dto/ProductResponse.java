package com.Market.MarketHub.Dto;

import com.Market.MarketHub.Enum.Category;
import com.Market.MarketHub.Enum.Unit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

    private Long id;
    private String name;
    private String description;
    private String brand;
    private String sku;
    private BigDecimal purchasePrice;
    private BigDecimal sellingPrice;
    private Double quantity;
    private Unit unit;
    private Category category;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
