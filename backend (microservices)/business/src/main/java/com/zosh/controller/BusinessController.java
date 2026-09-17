package com.zosh.controller;

import com.zosh.exception.UserException;
import com.zosh.mapper.BusinessMapper;
import com.zosh.modal.Business;
import com.zosh.payload.dto.BusinessDTO;
import com.zosh.payload.dto.UserDTO;
import com.zosh.service.BusinessService;
import com.zosh.service.clients.UserFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/businesses")
@RequiredArgsConstructor
public class BusinessController {

    private final BusinessService businessService;
    private final UserFeignClient userService;


    @PostMapping
    public ResponseEntity<BusinessDTO> createBusiness(
            @RequestHeader("Authorization") String jwt,
            @RequestBody BusinessDTO business) throws UserException {
        UserDTO user=userService.getUserFromJwtToken(jwt).getBody();


        Business createdBusiness = businessService.createBusiness(business,user);

        BusinessDTO businessDTO=BusinessMapper.mapToDTO(createdBusiness,user);

        return new ResponseEntity<>(businessDTO, HttpStatus.CREATED);
    }


    @PutMapping("/{businessId}")
    public ResponseEntity<BusinessDTO> updateBusiness(
            @PathVariable Long businessId,
            @RequestBody Business business
            ) throws Exception {
        Business updatedBusiness = businessService.updateBusiness(businessId, business);
        UserDTO user=userService.getUserById(updatedBusiness.getOwnerId()).getBody();

        BusinessDTO businessDTO=BusinessMapper.mapToDTO(updatedBusiness,user);

        return new ResponseEntity<>(businessDTO, HttpStatus.OK);


    }


    @GetMapping
    public ResponseEntity<List<BusinessDTO>> getAllBusinesses() throws UserException {
        List<Business> businesses = businessService.getAllBusinesses();
        List<BusinessDTO> businessDTOS = new ArrayList<>();
        for (Business business1 : businesses) {
            UserDTO owner = userService.getUserById(business1.getOwnerId()).getBody();
            BusinessDTO apply = BusinessMapper.mapToDTO(business1, owner);
            businessDTOS.add(apply);
        }
        return ResponseEntity.ok(businessDTOS);
    }


    @GetMapping("/{businessId}")
    public ResponseEntity<BusinessDTO> getBusinessById(@PathVariable Long businessId) throws Exception {
        Business business = businessService.getBusinessById(businessId);
        if (business==null) {
            throw new Exception("business not exist with id "+ businessId);
        }
        UserDTO user=userService.getUserById(business.getOwnerId()).getBody();

        BusinessDTO businessDTO=BusinessMapper.mapToDTO(business,user);

        return ResponseEntity.ok(businessDTO);
    }

    @GetMapping("/search")
    public ResponseEntity<List<BusinessDTO>> searchBusiness(
            @RequestParam("city") String city) throws Exception {
        List<Business> businesses = businessService.searchBusinessByCity(city);
        List<BusinessDTO> businessDTOS = new ArrayList<>();
        for (Business business1 : businesses) {
            UserDTO owner = userService.getUserById(business1.getOwnerId()).getBody();
            BusinessDTO apply = BusinessMapper.mapToDTO(business1, owner);
            businessDTOS.add(apply);
        }
        return ResponseEntity.ok(businessDTOS);
    }

    @GetMapping("/owner")
    public ResponseEntity<Business> getBusinessByOwner(
            @RequestHeader("Authorization")String jwt) throws Exception {
        UserDTO user=userService.getUserFromJwtToken(jwt).getBody();
        System.out.println("business "+user);
        Business business = businessService.getBusinessByOwnerId(user.getId());

        return ResponseEntity.ok(business);
    }
}
