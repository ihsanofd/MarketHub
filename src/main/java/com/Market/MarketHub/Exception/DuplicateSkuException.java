package com.Market.MarketHub.Exception;

import jakarta.validation.constraints.NotBlank;

public class DuplicateSkuException extends RuntimeException{
    public DuplicateSkuException(String s) {
        super(s);
    }
}
