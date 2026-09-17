package com.zosh.controller;

import com.zosh.modal.ServiceOffering;
import com.zosh.payload.dto.CategoryDTO;
import com.zosh.payload.dto.BusinessDTO;
import com.zosh.payload.dto.ServiceDTO;
import com.zosh.payload.dto.UserDTO;
import com.zosh.service.ServiceOfferingService;
import com.zosh.service.clients.CategoryFeignClient;
import com.zosh.service.clients.BusinessFeignClient;
import com.zosh.service.clients.UserFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/service-offering/business-owner")
public class BusinessServiceOfferingController {

    private final ServiceOfferingService serviceOfferingService;
    private final BusinessFeignClient businessService;
    private final CategoryFeignClient categoryService;

    @PostMapping
    public ResponseEntity<ServiceOffering> createService(
            @RequestHeader("Authorization") String jwt,
            @RequestBody ServiceDTO service) throws Exception {

        BusinessDTO business=businessService.getBusinessByOwner(jwt).getBody();

        CategoryDTO category=categoryService
                .getCategoryById(service.getCategory()).getBody();

        ServiceOffering createdService = serviceOfferingService
                .createService(service,business,category);
        return new ResponseEntity<>(createdService, HttpStatus.CREATED);
    }

    @PatchMapping("/{serviceId}")
    public ResponseEntity<ServiceOffering> updateService(
            @PathVariable Long serviceId,
            @RequestBody ServiceOffering service) throws Exception {
        ServiceOffering updatedService = serviceOfferingService
                .updateService(serviceId, service);
        if (updatedService != null) {
            return new ResponseEntity<>(updatedService, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
