package com.Market.MarketHub.Dto;

import com.Market.MarketHub.Enum.StoreCategory;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StoreRequest {

    @NotBlank
    private String storeName;

    @NotBlank
    private String address;

    @NotBlank
    private String city;

    @NotBlank
    private StoreCategory category;
}
