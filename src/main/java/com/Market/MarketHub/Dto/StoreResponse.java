package com.Market.MarketHub.Dto;

import com.Market.MarketHub.Enum.StoreCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StoreResponse {

    private Long id;
    private String storeName;
    private String address;
    private String city;
    private StoreCategory category;
    private LocalDateTime createdAt;
}
