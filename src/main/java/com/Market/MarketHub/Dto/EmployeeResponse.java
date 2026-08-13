package com.Market.MarketHub.Dto;

import com.Market.MarketHub.Enum.EmployeePosition;
import com.Market.MarketHub.Enum.EmployeeStatus;
import com.Market.MarketHub.Enum.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {

    private Long id;
    private String username;
    private String storeName;
    private EmployeePosition position;
    private EmployeeStatus status;
}
