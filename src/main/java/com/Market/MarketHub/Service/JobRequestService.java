package com.Market.MarketHub.Service;

import com.Market.MarketHub.Dto.CustomerJobRequestResponseDto;
import com.Market.MarketHub.Dto.JobRequestDto;
import com.Market.MarketHub.Dto.JobResponseDto;
import com.Market.MarketHub.Model.JobRequest;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface JobRequestService {
    JobResponseDto sendInvitation(JobRequestDto request , Authentication authentication);

    List<JobResponseDto> viewInvitations(Authentication authentication);

    JobResponseDto sendResponse(Long requestId, CustomerJobRequestResponseDto status, Authentication authentication);
}
