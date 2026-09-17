package com.zosh.service;

import com.zosh.modal.Business;
import com.zosh.payload.dto.BusinessDTO;
import com.zosh.payload.dto.UserDTO;

import java.util.List;

public interface BusinessService {


    Business createBusiness(BusinessDTO business, UserDTO user);

    Business updateBusiness(Long businessId, Business business) throws Exception;

    List<Business> getAllBusinesses();

    Business getBusinessById(Long businessId);

    Business getBusinessByOwnerId(Long ownerId);

    List<Business> searchBusinessByCity(String city);
}
