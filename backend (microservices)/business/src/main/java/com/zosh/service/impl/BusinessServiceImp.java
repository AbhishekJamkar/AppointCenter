package com.zosh.service.impl;

import com.zosh.modal.Business;
import com.zosh.payload.dto.BusinessDTO;
import com.zosh.payload.dto.UserDTO;
import com.zosh.repository.BusinessRepository;
import com.zosh.service.BusinessService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BusinessServiceImp implements BusinessService {

    private final BusinessRepository businessRepository;

    @Override
    public Business createBusiness(BusinessDTO req, UserDTO user) {

        Business business=new Business();
        business.setName(req.getName());
        business.setImages(req.getImages());
        business.setCity(req.getCity());
        business.setAddress(req.getAddress());
        business.setEmail(req.getEmail());
        business.setPhoneNumber(req.getPhoneNumber());
        business.setOpenTime(req.getOpenTime());
        business.setCloseTime(req.getCloseTime());
        business.setHomeService(true);
        business.setOpen(true);
        business.setOwnerId(user.getId());
        business.setActive(true);

        return businessRepository.save(business);
    }

    @Override
    public Business updateBusiness(Long businessId, Business business) throws Exception {

        Business existingBusiness = getBusinessById(businessId);
        if (existingBusiness!=null) {

            existingBusiness.setName(business.getName());
            existingBusiness.setAddress(business.getAddress());
            existingBusiness.setPhoneNumber(business.getPhoneNumber());
            existingBusiness.setEmail(business.getEmail());
            existingBusiness.setCity(business.getCity());
            existingBusiness.setOpen(business.isOpen());
            existingBusiness.setHomeService(business.isHomeService());
            existingBusiness.setActive(business.isActive());
            existingBusiness.setOpenTime(business.getOpenTime());
            existingBusiness.setCloseTime(business.getCloseTime());

            return businessRepository.save(existingBusiness);
        }
        throw new Exception("business not exist");
    }

    @Override
    public List<Business> getAllBusinesses() {
        return businessRepository.findAll();
    }

    @Override
    public Business getBusinessById(Long businessId) {
        return businessRepository.findById(businessId).orElse(null);
    }

    @Override
    public Business getBusinessByOwnerId(Long ownerId) {
        return businessRepository.findByOwnerId(ownerId);
    }

    @Override
    public List<Business> searchBusinessByCity(String city) {
        return businessRepository.searchBusinesses(city);
    }


}
