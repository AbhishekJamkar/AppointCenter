package com.zosh.mapper;

import com.zosh.modal.Booking;
import com.zosh.payload.dto.BookingDTO;
import com.zosh.payload.dto.BusinessDTO;
import com.zosh.payload.dto.ServiceOfferingDTO;
import com.zosh.payload.dto.UserDTO;

import java.util.Set;
import java.util.stream.Collectors;

public class BookingMapper {

    // Convert Booking entity to BookingDTO
    public static BookingDTO toDTO(Booking booking,
                                   Set<ServiceOfferingDTO> serviceOfferingDTOS,
                                   BusinessDTO businessDTO,
                                   UserDTO userDTO
                                   ) {
        if (booking == null) {
            return null;
        }

        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setId(booking.getId());
        bookingDTO.setBusinessId(booking.getBusinessId());
        bookingDTO.setCustomerId(booking.getCustomerId());
        bookingDTO.setStartTime(booking.getStartTime());
        bookingDTO.setEndTime(booking.getEndTime());
        bookingDTO.setServicesIds(booking.getServiceIds());
        bookingDTO.setStatus(booking.getStatus());
        bookingDTO.setTotalPrice(booking.getTotalPrice());

        // If services mapping is needed (convert from serviceIds to ServiceOfferingDTOs)
        bookingDTO.setServices(serviceOfferingDTOS);
        bookingDTO.setCustomer(userDTO);
        bookingDTO.setBusiness(businessDTO);

        return bookingDTO;
    }

    // Convert BookingDTO to Booking entity
    public static Booking toEntity(BookingDTO bookingDTO) {
        if (bookingDTO == null) {
            return null;
        }

        Booking booking = new Booking();
        booking.setId(bookingDTO.getId());
        booking.setBusinessId(bookingDTO.getBusinessId());
        booking.setCustomerId(bookingDTO.getCustomerId());
        booking.setStartTime(bookingDTO.getStartTime());
        booking.setEndTime(bookingDTO.getEndTime());
        booking.setServiceIds(bookingDTO.getServicesIds());
        booking.setStatus(bookingDTO.getStatus());
        booking.setTotalPrice(bookingDTO.getTotalPrice());

        // Additional logic for mapping services can be added here if needed.

        return booking;
    }
}