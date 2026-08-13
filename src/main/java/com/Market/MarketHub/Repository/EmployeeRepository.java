package com.Market.MarketHub.Repository;

import com.Market.MarketHub.Model.StoreEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<StoreEmployee , Long> {
    boolean existsByUserId(Integer id);
}
