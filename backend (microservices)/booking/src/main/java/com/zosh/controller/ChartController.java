package com.zosh.controller;

import com.zosh.domain.BookingStatus;
import com.zosh.exception.UserException;
import com.zosh.modal.Booking;
import com.zosh.payload.dto.BookingDTO;
import com.zosh.payload.dto.BusinessDTO;
import com.zosh.payload.dto.UserDTO;

import com.zosh.service.BookingService;
import com.zosh.service.clients.BusinessFeignClient;
import com.zosh.service.impl.BookingChartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bookings/chart")
public class ChartController {

    private final BookingChartService bookingChartService;
    private final BookingService bookingService;
    private final BusinessFeignClient businessService;

    @GetMapping("/earnings")
    public ResponseEntity<List<Map<String, Object>>> getEarningsChartData(
            @RequestHeader("Authorization") String jwt) throws Exception {

//        UserDTO user = userService.getUserFromJwtToken(jwt).getBody();

        BusinessDTO business = businessService.getBusinessByOwner(jwt).getBody();
        List<Booking> bookings=bookingService.getBookingsByBusiness(business.getId());

        // Generate chart data
        List<Map<String, Object>> chartData = bookingChartService
                .generateEarningsChartData(bookings);

        return ResponseEntity.ok(chartData);

    }

    @GetMapping("/bookings")
    public ResponseEntity<List<Map<String, Object>>> getBookingsChartData(
            @RequestHeader("Authorization") String jwt) throws Exception {

        BusinessDTO business = businessService.getBusinessByOwner(jwt).getBody();
        List<Booking> bookings=bookingService.getBookingsByBusiness(business.getId());
        // Generate chart data
        List<Map<String, Object>> chartData = bookingChartService.generateBookingCountChartData(bookings);

        return ResponseEntity.ok(chartData);

    }
}
