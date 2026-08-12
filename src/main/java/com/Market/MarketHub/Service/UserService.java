package com.Market.MarketHub.Service;

import com.Market.MarketHub.Dto.UserRequest;
import com.Market.MarketHub.Dto.UserResponse;
import com.Market.MarketHub.Enum.Role;
import com.Market.MarketHub.Enum.UserStatus;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;

public interface UserService {


    UserResponse createUser(UserRequest request);
  //  UserResponse updateUserRole(Role role);
}
