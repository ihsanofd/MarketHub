package com.Market.MarketHub.ServiceImpl;

import com.Market.MarketHub.Dto.CustomerJobRequestResponseDto;
import com.Market.MarketHub.Dto.JobRequestDto;
import com.Market.MarketHub.Dto.JobResponseDto;
import com.Market.MarketHub.Enum.JobRequestStatus;
import com.Market.MarketHub.Exception.JobRequestNotFoundException;
import com.Market.MarketHub.Exception.StoreNotFoundException;
import com.Market.MarketHub.Model.JobRequest;
import com.Market.MarketHub.Model.Store;
import com.Market.MarketHub.Model.User;
import com.Market.MarketHub.Repository.JobRequestRepository;
import com.Market.MarketHub.Repository.StoreRepository;
import com.Market.MarketHub.Repository.UserRepository;
import com.Market.MarketHub.Service.JobRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class JobRequestServiceImpl implements JobRequestService {

    @Autowired
    private JobRequestRepository jobRequestRepository;
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private UserRepository userRepository;


    @Override
    public JobResponseDto sendInvitation(JobRequestDto request , Authentication authentication) {

        String owner=authentication.getName();
        Store store=storeRepository.findByOwner_Username(owner).orElseThrow(
                ()->new StoreNotFoundException("Only Store_Owner can send job invitation."));

        User customer=userRepository.findById(request.getCustomerId()).orElseThrow(
                ()->new UsernameNotFoundException("Sorry, the user does not exist"));

        JobRequest jobRequest=new JobRequest();
        jobRequest.setUser(customer);
        jobRequest.setStore(store);
        jobRequest.setPosition(request.getPosition());
        jobRequest.setStatus(JobRequestStatus.PENDING);

        JobRequest saved=jobRequestRepository.save(jobRequest);

        return jobResponse(saved);
    }




    @Override
    public List<JobResponseDto> viewInvitations(Authentication authentication) {

        String username=authentication.getName();

        List<JobRequest> invitations=jobRequestRepository.findByUser_Username(username);

        List<JobResponseDto> responses=new ArrayList<>();

        for (JobRequest invitation:invitations){

            responses.add(jobResponse(invitation));
        }
        return responses;
    }




    @Override
    public JobResponseDto sendResponse(Long requestId, CustomerJobRequestResponseDto status, Authentication authentication) {

        JobRequest jobRequest=jobRequestRepository.findById(requestId)
                .orElseThrow(()->new JobRequestNotFoundException("JobRequest does not exist"));

        if (jobRequest.getStatus() != JobRequestStatus.PENDING){
            throw new IllegalStateException("This invitation has already been responded to");
        }

        if (status.getStatus() != JobRequestStatus.ACCEPTED && status.getStatus() != JobRequestStatus.REJECTED) {
            throw new IllegalArgumentException("Response must be either ACCEPTED or REJECTED");
        }

        jobRequest.setStatus(status.getStatus());
        jobRequest.setRespondedAt(LocalDateTime.now());

        JobRequest saved = jobRequestRepository.save(jobRequest);
        return jobResponse(saved);

    }




    public JobResponseDto jobResponse(JobRequest request){

        JobResponseDto dto=new JobResponseDto();
        dto.setId(request.getId());
        dto.setPosition(request.getPosition());
        dto.setStoreName(request.getStore().getStoreName());
        dto.setStatus(request.getStatus().name());

        return dto;
    }

}
