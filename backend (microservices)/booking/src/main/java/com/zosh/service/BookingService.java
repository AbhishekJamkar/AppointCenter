package com.zosh.service;

import com.zosh.domain.BookingStatus;
import com.zosh.modal.*;
import com.zosh.payload.dto.BusinessDTO;
import com.zosh.payload.dto.ServiceOfferingDTO;
import com.zosh.payload.dto.UserDTO;
import com.zosh.payload.request.BookingRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface BookingService {


    Booking createBooking(
            BookingRequest booking,
            UserDTO user,
            BusinessDTO business,
            Set<ServiceOfferingDTO> serviceOfferingSet) throws Exception;


    List<Booking> getBookingsByCustomer(Long customerId);


    List<Booking> getBookingsByBusiness(Long businessId);


    Booking getBookingById(Long bookingId);

    Booking bookingSucess(PaymentOrder order);


    Booking updateBookingStatus(Long bookingId, BookingStatus status) throws Exception;

    BusinessReport getBusinessReport(Long businessId);

    List<Booking> getBookingsByDate(LocalDate date,Long businessId);
}
