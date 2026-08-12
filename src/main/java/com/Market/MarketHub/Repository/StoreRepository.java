package com.Market.MarketHub.Repository;

import com.Market.MarketHub.Model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {



    Optional<Store> findByOwner_Username(String username);
}
