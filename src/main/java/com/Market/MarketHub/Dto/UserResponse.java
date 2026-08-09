package com.Market.MarketHub.Dto;

import com.Market.MarketHub.Enum.Role;
import com.Market.MarketHub.Enum.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Integer id;
    private String username;
    private LocalDateTime createdAt;
    private UserStatus userStatus;
    private Role role;
}
