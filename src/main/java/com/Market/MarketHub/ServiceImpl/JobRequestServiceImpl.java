package com.Market.MarketHub.ServiceImpl;

import com.Market.MarketHub.Dto.*;
import com.Market.MarketHub.Enum.EmployeeStatus;
import com.Market.MarketHub.Enum.JobRequestStatus;
import com.Market.MarketHub.Enum.Role;
import com.Market.MarketHub.Enum.UserStatus;
import com.Market.MarketHub.Exception.JobRequestNotFoundException;
import com.Market.MarketHub.Exception.StoreNotFoundException;
import com.Market.MarketHub.Model.JobRequest;
import com.Market.MarketHub.Model.Store;
import com.Market.MarketHub.Model.StoreEmployee;
import com.Market.MarketHub.Model.User;
import com.Market.MarketHub.Repository.EmployeeRepository;
import com.Market.MarketHub.Repository.JobRequestRepository;
import com.Market.MarketHub.Repository.StoreRepository;
import com.Market.MarketHub.Repository.UserRepository;
import com.Market.MarketHub.Service.JobRequestService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
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
    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public JobResponseDto sendInvitation(JobRequestDto request , Authentication authentication) {

        String owner=authentication.getName();
        Store store=storeRepository.findByOwner_Username(owner).orElseThrow(
                ()->new StoreNotFoundException("Only Store_Owner can send job invitation."));

        User customer=userRepository.findById(request.getCustomerId()).orElseThrow(
                ()->new UsernameNotFoundException("Sorry, the user does not exist"));

        if (customer.getRole() != Role.CUSTOMER){
            throw new IllegalArgumentException("the user is not customer");
        }

        if (customer.getUserStatus() != UserStatus.ACTIVE) {
            throw new IllegalArgumentException(
                    "Only active customers can receive job invitations"
            );
        }

        if (jobRequestRepository.existsByUserIdAndStoreIdAndStatus(
                customer.getId(),
                store.getId(),
                JobRequestStatus.PENDING)) {

            throw new IllegalArgumentException(
                    "A pending invitation already exists for this customer"
            );
        }

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

        if(!jobRequest.getUser().getUsername().equals(authentication.getName())) {
            throw new AccessDeniedException("You can't respond to this request");
        }

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



    @Override
    @Transactional
    public EmployeeResponse approveJobRequest(Long requestId, Authentication authentication) {

        JobRequest jobRequest=jobRequestRepository.findById(requestId)
                .orElseThrow(()->new JobRequestNotFoundException("Job request not found"));

        String owner=authentication.getName();
        Store store=storeRepository.findByOwner_Username(owner).orElseThrow(
                ()->new IllegalArgumentException("Only Store_Owner can approve accepted request."));


        if (!jobRequest.getStore().getId().equals(store.getId())) {
            throw new AccessDeniedException(
                    "You cannot approve a request for another store"
            );
        }

        if (jobRequest.getStatus()!= JobRequestStatus.ACCEPTED){
            throw new IllegalArgumentException("only accepted request can be approve");
        }
        User user=jobRequest.getUser();

        if (employeeRepository.existsByUserId(user.getId())) {
            throw new IllegalArgumentException("User is already an employee");
        }

        if(user.getUserStatus() != UserStatus.ACTIVE){
            throw new IllegalArgumentException("Customer account is not active");
        }

        if (user.getRole() != Role.CUSTOMER){
            throw new IllegalArgumentException("only customer request can be approve");
        }

        user.setRole(Role.EMPLOYEE);
        userRepository.save(user);
        jobRequest.setStatus(JobRequestStatus.APPROVED);
        jobRequestRepository.save(jobRequest);

        StoreEmployee employee=new StoreEmployee();

        employee.setUser(user);
        employee.setStore(store);
        employee.setPosition(jobRequest.getPosition());
        employee.setStatus(EmployeeStatus.ACTIVE);
        StoreEmployee saved=employeeRepository.save(employee);

        return employeeResponse(saved);

    }




    public JobResponseDto jobResponse(JobRequest request){

        JobResponseDto dto=new JobResponseDto();
        dto.setId(request.getId());
        dto.setPosition(request.getPosition());
        dto.setStoreName(request.getStore().getStoreName());
        dto.setStatus(request.getStatus().name());

        return dto;
    }

    public EmployeeResponse employeeResponse(StoreEmployee employee){
        EmployeeResponse response=new EmployeeResponse();
        response.setId(employee.getId());
        response.setUsername(employee.getUser().getUsername());
        response.setStoreName(employee.getStore().getStoreName());
        response.setPosition(employee.getPosition());
        response.setStatus(employee.getStatus());

        return response;
    }

}
