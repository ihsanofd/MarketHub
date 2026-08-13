package com.Market.MarketHub.Service;

import com.Market.MarketHub.Dto.*;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface JobRequestService {
    JobResponseDto sendInvitation(JobRequestDto request , Authentication authentication);

    List<JobResponseDto> viewInvitations(Authentication authentication);

    JobResponseDto sendResponse(Long requestId, CustomerJobRequestResponseDto status, Authentication authentication);

    EmployeeResponse approveJobRequest(Long requestId, Authentication authentication);
}
