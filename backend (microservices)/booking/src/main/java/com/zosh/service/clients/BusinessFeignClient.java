package com.zosh.service.clients;

import com.zosh.payload.dto.BusinessDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient("SALON")
public interface BusinessFeignClient {

    @GetMapping("/api/businesses/owner")
    public ResponseEntity<BusinessDTO> getBusinessByOwner(
            @RequestHeader("Authorization")String jwt) throws Exception;

    @GetMapping("/api/businesses/{businessId}")
    public ResponseEntity<BusinessDTO> getBusinessById(@PathVariable Long businessId) throws Exception;
}
