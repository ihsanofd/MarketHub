package com.Market.MarketHub.Repository;

import com.Market.MarketHub.Model.JobRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRequestRepository extends JpaRepository<JobRequest , Long> {
    List<JobRequest> findByUser_Username(String username);
}
