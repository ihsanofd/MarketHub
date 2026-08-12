package com.Market.MarketHub.Service;

import com.Market.MarketHub.Dto.CustomerProductResponse;
import com.Market.MarketHub.Dto.ProductRequest;
import com.Market.MarketHub.Dto.ProductResponse;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ProductService {

    ProductResponse addProduct(ProductRequest request, Authentication authentication);

    List<CustomerProductResponse> customerProductView(String storeName);
}
