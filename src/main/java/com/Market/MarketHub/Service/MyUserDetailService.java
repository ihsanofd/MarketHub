package com.Market.MarketHub.Service;

import com.Market.MarketHub.Model.User;
import com.Market.MarketHub.Model.UserPrincipal;
import com.Market.MarketHub.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user=userRepository.findByUsername(username).orElseThrow(
                ()->new UsernameNotFoundException("User Not Found with :" + username));
        return new UserPrincipal(user);
    }
}
