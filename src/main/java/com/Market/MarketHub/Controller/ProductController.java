package com.Market.MarketHub.Controller;

import com.Market.MarketHub.Dto.CustomerProductResponse;
import com.Market.MarketHub.Dto.ProductRequest;
import com.Market.MarketHub.Dto.ProductResponse;
import com.Market.MarketHub.Service.ProductService;
import jakarta.validation.Valid;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class ProductController {

    @Autowired
    private ProductService productService;
    Authentication authentication;

    @PostMapping
    public ResponseEntity<ProductResponse> addProduct(@Valid @RequestBody ProductRequest request , Authentication authentication){
        ProductResponse response=productService.addProduct(request , authentication);
        return new ResponseEntity<>(response , HttpStatus.CREATED);
    }

    @GetMapping("products/{storeName}")
    public ResponseEntity<List<CustomerProductResponse>>  cuProductView(@PathVariable String storeName){
        List<CustomerProductResponse> productResponses=productService.customerProductView(storeName);
        return new ResponseEntity<>(productResponses , HttpStatus.OK);
    }
//
//    @GetMapping("products/{productId}")
//    public ResponseEntity<CustomerProductResponse> cuProductViewById(){
//        List<CustomerProductResponse> productResponses=productService.customerProductView();
//        return new ResponseEntity<>(productResponses , HttpStatus.OK);
//    }

}
