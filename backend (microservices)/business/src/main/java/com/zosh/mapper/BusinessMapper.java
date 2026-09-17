package com.zosh.mapper;

import com.zosh.modal.Business;
import com.zosh.payload.dto.BusinessDTO;
import com.zosh.payload.dto.UserDTO;
import org.springframework.stereotype.Service;


public class BusinessMapper {

    public static BusinessDTO mapToDTO(Business business, UserDTO userDTO) {
        if (business == null) {
            return null;
        }

        BusinessDTO businessDTO = new BusinessDTO();
        businessDTO.setId(business.getId());
        businessDTO.setName(business.getName());
        businessDTO.setAddress(business.getAddress());
        businessDTO.setPhoneNumber(business.getPhoneNumber());
        businessDTO.setEmail(business.getEmail());
        businessDTO.setCity(business.getCity());
//        businessDTO.setIsOpen(business.isOpen());
        businessDTO.setHomeService(business.isHomeService());
        businessDTO.setActive(business.isActive());
        businessDTO.setOwnerId(business.getOwnerId());
        businessDTO.setOpenTime(business.getOpenTime());
        businessDTO.setCloseTime(business.getCloseTime());
        businessDTO.setImages(business.getImages());
        businessDTO.setOwner(userDTO);

        return businessDTO;
    }

}
