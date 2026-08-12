package com.Market.MarketHub.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobResponseDto {

    private Long id;
    private String storeName;
    private String position;
    private String status;

}
