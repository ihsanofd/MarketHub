package com.Market.MarketHub.Service;

import com.Market.MarketHub.Dto.StoreRequest;
import com.Market.MarketHub.Dto.StoreResponse;
import org.springframework.security.core.Authentication;

public interface StoreService {

    StoreResponse addStore(StoreRequest request , Authentication authentication);
}
