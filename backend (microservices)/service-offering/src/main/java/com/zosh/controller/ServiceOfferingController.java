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

import java.util.Set;

@RequestMapping("/api/service-offering")
@RestController
@RequiredArgsConstructor
public class ServiceOfferingController {


    private final ServiceOfferingService serviceOfferingService;


    @GetMapping("/business/{businessId}")
    public ResponseEntity<Set<ServiceOffering>> getServicesByBusinessId(
            @PathVariable Long businessId,
            @RequestParam(required = false) Long categoryId) {
        Set<ServiceOffering> services =  serviceOfferingService
                .getAllServicesByBusinessId(businessId,categoryId);

            return ResponseEntity.ok(services);

    }


    @GetMapping("/{serviceId}")
    public ResponseEntity<ServiceOffering> getServiceById(@PathVariable Long serviceId) throws Exception {
        ServiceOffering service = serviceOfferingService
                .getServiceById(serviceId);
        if (service == null) {
            throw new Exception("service not found with id "+serviceId);
        }
            return ResponseEntity.ok(service);

    }

    @GetMapping("/list/{ids}")
    public ResponseEntity<Set<ServiceOffering>> getServicesByIds(
            @PathVariable Set<Long> ids) {
        Set<ServiceOffering> services =  serviceOfferingService
                .getServicesByIds(ids);

        return ResponseEntity.ok(services);


    }


}
