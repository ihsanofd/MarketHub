package com.Market.MarketHub.Dto;

import com.Market.MarketHub.Enum.Category;
import com.Market.MarketHub.Enum.Unit;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    @NotBlank
    private String name;

    private String description;

    @NotBlank
    private String brand;

    @NotBlank
    private String sku;

    @NotNull
    @Positive
    private BigDecimal purchasePrice;

    @NotNull
    @Positive
    private BigDecimal sellingPrice;

    @NotNull
    @PositiveOrZero
    private Double quantity;

    @NotNull
    private Unit unit;

    @NotNull
    private Category category;
}