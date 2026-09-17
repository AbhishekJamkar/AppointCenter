package com.zosh.modal;

import lombok.Data;

@Data
public class BusinessReport {
    private Long businessId;
    private String businessName;
    private Double totalEarnings;
    private Integer totalBookings;
    private Integer cancelledBookings;
    private Double totalRefund;


}
