package com.Market.MarketHub.Controller;

import com.Market.MarketHub.Dto.CustomerJobRequestResponseDto;
import com.Market.MarketHub.Dto.JobRequestDto;
import com.Market.MarketHub.Dto.JobResponseDto;
import com.Market.MarketHub.Service.JobRequestService;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class JobRequestController {

    @Autowired
    private JobRequestService jobRequestService;
    private Authentication authentication;


    @PostMapping("/jobRequest")
    public ResponseEntity<JobResponseDto> sendInvitation(@RequestBody JobRequestDto request , Authentication authentication){
        JobResponseDto response=jobRequestService.sendInvitation(request ,authentication);
        return new ResponseEntity<>(response , HttpStatus.CREATED);
    }

    @GetMapping("/my")
    public ResponseEntity<List<JobResponseDto>> viewInvitations(Authentication authentication){
        List<JobResponseDto> responses=jobRequestService.viewInvitations(authentication);
        return new ResponseEntity<>(responses , HttpStatus.OK);
    }

    @PostMapping("/response/{requestId}")
    public ResponseEntity<JobResponseDto> customerResponse(@PathVariable Long requestId , @RequestBody CustomerJobRequestResponseDto status, Authentication authentication){
        JobResponseDto responseDto=jobRequestService.sendResponse(requestId ,status, authentication);
        return new ResponseEntity<>(responseDto , HttpStatus.CREATED);


    }

}
