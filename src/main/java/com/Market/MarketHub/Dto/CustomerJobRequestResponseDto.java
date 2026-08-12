package com.Market.MarketHub.Dto;

import com.Market.MarketHub.Enum.JobRequestStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CustomerJobRequestResponseDto {

    @NotNull(message = "Status is required")
    private JobRequestStatus status;
}