package com.Market.MarketHub.ServiceImpl;

import com.Market.MarketHub.Dto.StoreRequest;
import com.Market.MarketHub.Dto.StoreResponse;
import com.Market.MarketHub.Enum.Role;
import com.Market.MarketHub.Enum.StoreStatus;
import com.Market.MarketHub.Model.Store;
import com.Market.MarketHub.Model.User;
import com.Market.MarketHub.Repository.StoreRepository;
import com.Market.MarketHub.Repository.UserRepository;
import com.Market.MarketHub.Service.StoreService;
import com.Market.MarketHub.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private UserService userService;



    @Override
    public StoreResponse addStore(StoreRequest request , Authentication authentication) {

        String username=authentication.getName();
        User owner=userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("User not found"));

        Store store=new Store();
        store.setStoreName(request.getStoreName());
        store.setCity(request.getCity());
        store.setAddress(request.getAddress());
        store.setCategory(request.getCategory());
        store.setStoreStatus(StoreStatus.PENDING);
        store.setOwner(owner);

        owner.setRole(Role.STORE_OWNER);
        userRepository.save(owner);

        Store savedStore=storeRepository.save(store);
        return mapToResponse(savedStore);
    }




    public StoreResponse mapToResponse(Store store){

        return new StoreResponse(
                store.getId(),
                store.getStoreName(),
                store.getCity(),
                store.getAddress(),
                store.getCategory(),
                store.getCreatedAt());
    }
}
