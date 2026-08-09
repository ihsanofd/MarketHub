package com.Market.MarketHub.Service;

import com.Market.MarketHub.Dto.UserRequest;
import com.Market.MarketHub.Dto.UserResponse;

public interface UserService {


    UserResponse createUser(UserRequest request);
}
