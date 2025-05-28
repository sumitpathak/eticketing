package com.entbooking.eticketing.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookingRequest {
    private String userId;
    private Long showId;
    private List<Long> seatIds;
}