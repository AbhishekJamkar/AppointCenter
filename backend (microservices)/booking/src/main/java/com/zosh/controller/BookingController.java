package com.zosh.controller;

import com.zosh.domain.BookingStatus;
import com.zosh.domain.PaymentMethod;
import com.zosh.exception.UserException;
import com.zosh.mapper.BookingMapper;
import com.zosh.modal.*;
import com.zosh.payload.dto.*;
import com.zosh.payload.request.BookingRequest;
import com.zosh.payload.response.PaymentLinkResponse;
import com.zosh.service.*;
import com.zosh.service.clients.PaymentFeignClient;
import com.zosh.service.clients.BusinessFeignClient;
import com.zosh.service.clients.ServiceOfferingFeignClient;
import com.zosh.service.clients.UserFeignClient;
import jakarta.ws.rs.Path;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;
    private final UserFeignClient userService;
    private final BusinessFeignClient businessService;
    private final ServiceOfferingFeignClient serviceOfferingService;
    private final PaymentFeignClient paymentService;
    private final UserFeignClient userFeignClient;


    @PostMapping
    public ResponseEntity<PaymentLinkResponse> createBooking(
            @RequestHeader("Authorization") String jwt,
            @RequestParam Long businessId,
            @RequestParam PaymentMethod paymentMethod,
            @RequestBody BookingRequest bookingRequest) throws Exception {


        UserDTO user = userService.getUserFromJwtToken(jwt).getBody();

        BusinessDTO business = businessService.getBusinessById(businessId).getBody();

        if(business.getId()==null){
            throw new Exception("Business not found");
        }

        Set<ServiceOfferingDTO> services = serviceOfferingService
                .getServicesByIds(bookingRequest.getServiceIds()).getBody();


        Booking createdBooking = bookingService.createBooking(
                bookingRequest,
                user,
                business,
                services
        );
        PaymentLinkResponse res=paymentService.createPaymentLink(
                jwt,
                createdBooking,
                paymentMethod
        ).getBody();


        return new ResponseEntity<>(res, HttpStatus.CREATED);

    }

    /**
     * Get all bookings for a customer
     */
    @GetMapping("/customer")
    public ResponseEntity<Set<BookingDTO>> getBookingsByCustomer(
            @RequestHeader("Authorization") String jwt)
            throws UserException {

            UserDTO user = userService.getUserFromJwtToken(jwt).getBody();

            List<Booking> bookings = bookingService.getBookingsByCustomer(user.getId());

        return ResponseEntity.ok(getBookingDTOs(bookings));

    }

    /**
     * Get all bookings for a business
     */
    @GetMapping("/report")
    public ResponseEntity<BusinessReport> getBusinessReport(
            @RequestHeader("Authorization") String jwt
    ) throws Exception {

        UserDTO user = userService.getUserFromJwtToken(jwt).getBody();

        BusinessDTO business = businessService.getBusinessByOwner(jwt).getBody();

        BusinessReport report = bookingService.getBusinessReport(business.getId());


        return ResponseEntity.ok(report);

    }

    @GetMapping("/business")
    public ResponseEntity<Set<BookingDTO>> getBookingsByBusiness (

            @RequestHeader("Authorization") String jwt
    ) throws Exception {

        UserDTO user = userService.getUserFromJwtToken(jwt).getBody();

        BusinessDTO business = businessService.getBusinessByOwner(jwt).getBody();

        List<Booking> bookings = bookingService.getBookingsByBusiness(business.getId());


        return ResponseEntity.ok(getBookingDTOs(bookings));


    }



    private Set<BookingDTO> getBookingDTOs(List<Booking> bookings) {

        return bookings.stream()
                .map(booking -> {
                    UserDTO user;
                    Set<ServiceOfferingDTO> offeringDTOS=serviceOfferingService
                            .getServicesByIds(booking.getServiceIds()).getBody();

                    BusinessDTO businessDTO;
                    try {
                        businessDTO=businessService.getBusinessById(
                                booking.getBusinessId()
                        ).getBody();
                        user= userFeignClient.getUserById(booking.getCustomerId()).getBody();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                    return BookingMapper.toDTO(
                            booking,
                            offeringDTOS,
                            businessDTO,user
                    );
                })
                .collect(Collectors.toSet());
    }

    /**
     * Get a booking by its ID
     */
    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingDTO> getBookingById(@PathVariable Long bookingId) {
        Booking booking = bookingService.getBookingById(bookingId);
        Set<ServiceOfferingDTO> offeringDTOS=serviceOfferingService
                .getServicesByIds(booking.getServiceIds()).getBody();

        BookingDTO bookingDTO=BookingMapper.toDTO(booking,
                offeringDTOS,null,null);

            return ResponseEntity.ok(bookingDTO);


    }

    /**
     * Update the status of a booking
     */
    @PutMapping("/{bookingId}/status")
    public ResponseEntity<BookingDTO> updateBookingStatus(
            @PathVariable Long bookingId,
            @RequestParam BookingStatus status) throws Exception {



        Booking updatedBooking = bookingService.updateBookingStatus(bookingId, status);

        Set<ServiceOfferingDTO> offeringDTOS=serviceOfferingService
                .getServicesByIds(updatedBooking.getServiceIds()).getBody();

        BusinessDTO businessDTO;
        try {
            businessDTO=businessService.getBusinessById(
                    updatedBooking.getBusinessId()
            ).getBody();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        BookingDTO bookingDTO=BookingMapper.toDTO(updatedBooking,
                offeringDTOS,
                businessDTO,null);

        return new ResponseEntity<>(bookingDTO, HttpStatus.OK);

    }

    @GetMapping("/slots/business/{businessId}/date/{date}")
    public ResponseEntity<List<BookedSlotsDTO>> getBookedSlots (
            @PathVariable Long businessId,
            @PathVariable LocalDate date,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {

        List<Booking> bookings = bookingService.getBookingsByDate(date,businessId);

        List<BookedSlotsDTO> slotsDTOS = bookings.stream()
                .map(booking -> {
                    BookedSlotsDTO slotDto = new BookedSlotsDTO();

                    slotDto.setStartTime(booking.getStartTime());
                    slotDto.setEndTime(booking.getEndTime());


                    return slotDto;
                })
                .toList();


        return ResponseEntity.ok(slotsDTOS);


    }
}
