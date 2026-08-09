package com.Market.MarketHub.Controller;

import com.Market.MarketHub.Dto.StoreRequest;
import com.Market.MarketHub.Dto.StoreResponse;
import com.Market.MarketHub.Service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @PostMapping
    public ResponseEntity<StoreResponse> addStore(@RequestBody StoreRequest request , Authentication authentication){
        StoreResponse response=storeService.addStore(request , authentication);
        return new ResponseEntity<>(response , HttpStatus.CREATED);
    }
}
