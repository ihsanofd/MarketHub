package com.Market.MarketHub.Repository;

import com.Market.MarketHub.Model.Product;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsBySku(@NotBlank String sku);

    List<Product> findByStore_StoreNameIgnoreCase(String storeName);
}
