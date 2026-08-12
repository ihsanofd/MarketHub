package com.Market.MarketHub.Exception;

public class JobRequestNotFoundException extends  RuntimeException {
    public JobRequestNotFoundException(String string) {
        super(string);
    }
}
