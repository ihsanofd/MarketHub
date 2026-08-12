package com.Market.MarketHub.Exception;

public class StoreNotFoundException extends RuntimeException {
    public StoreNotFoundException(String storeNotFound) {
        super(storeNotFound);
    }
}
