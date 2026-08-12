package com.Market.MarketHub.ServiceImpl;

import com.Market.MarketHub.Dto.CustomerProductResponse;
import com.Market.MarketHub.Dto.ProductRequest;
import com.Market.MarketHub.Dto.ProductResponse;
import com.Market.MarketHub.Exception.DuplicateSkuException;
import com.Market.MarketHub.Exception.StoreNotFoundException;
import com.Market.MarketHub.Model.Product;
import com.Market.MarketHub.Model.Store;
import com.Market.MarketHub.Repository.ProductRepository;
import com.Market.MarketHub.Repository.StoreRepository;
import com.Market.MarketHub.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private ProductRepository productRepository;


    @Override
    public ProductResponse addProduct(ProductRequest request, Authentication authentication) {

        String username=authentication.getName();
        Store store=storeRepository.findByOwner_Username(username).orElseThrow(()->new StoreNotFoundException("Store not found"));

        if (productRepository.existsBySku(request.getSku())) {
            throw new DuplicateSkuException("SKU already exists: " + request.getSku());
        }

        Product product=new Product();

        product.setName(request.getName());
        product.setBrand(request.getBrand());
        product.setSku(request.getSku());
        product.setDescription(request.getDescription());
        product.setPurchasePrice(request.getPurchasePrice());
        product.setSellingPrice(request.getSellingPrice());
        product.setQuantity(request.getQuantity());
        product.setCategory(request.getCategory());
        product.setUnit(request.getUnit());

        product.setStore(store);

        Product savedProduct=productRepository.save(product);
        return mapToResponse(savedProduct);
    }


    @Override
    public List<CustomerProductResponse> customerProductView(String storeName) {

        List<Product> products=productRepository.findByStore_StoreNameIgnoreCase(storeName);
        List<CustomerProductResponse> customerProductResponses=new ArrayList<>();

        for (Product product:products){
            customerProductResponses.add(customerProductResponse(product));
        }

        return customerProductResponses;
    }


    public ProductResponse mapToResponse(Product product){
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getBrand(),
                product.getSku(),
                product.getPurchasePrice(),
                product.getSellingPrice(),
                product.getQuantity(),
                product.getUnit(),
                product.getCategory(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }

    public CustomerProductResponse customerProductResponse(Product product ){
        return new CustomerProductResponse(
        product.getId(),
                product.getName(),
                product.getSellingPrice(),
                product.getDescription(),
                product.getQuantity(),
                product.getBrand()
        );
    }
}
