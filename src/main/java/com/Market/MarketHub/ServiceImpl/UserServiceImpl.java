package com.Market.MarketHub.ServiceImpl;

import com.Market.MarketHub.Dto.UserRequest;
import com.Market.MarketHub.Dto.UserResponse;
import com.Market.MarketHub.Enum.Role;
import com.Market.MarketHub.Enum.UserStatus;
import com.Market.MarketHub.Model.User;
import com.Market.MarketHub.Repository.UserRepository;
import com.Market.MarketHub.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    Authentication authentication;

    @Override
    public UserResponse createUser(UserRequest request) {

        User user=new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setRole(Role.CUSTOMER);
        user.setUserStatus(UserStatus.ACTIVE);

        User savedUser=userRepository.save(user);

        return mapToResponse(savedUser);
    }
//
//    @Override
//    public UserResponse updateUserRole(Role role) {
//        String username=authentication.getName();
//        User user=userRepository.findByUsername(username).orElseThrow(()->
//                new UsernameNotFoundException("user not found"));
//
//        user.setRole(role);
//
//        User updatedUser=userRepository.save(user);
//
//        return mapToResponse(updatedUser);
//    }


    public UserResponse mapToResponse(User user){

        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getCreatedAt(),
                user.getUserStatus(),
                user.getRole()
        );
    }
}
