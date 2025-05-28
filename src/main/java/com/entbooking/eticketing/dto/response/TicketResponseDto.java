package com.entbooking.eticketing.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketResponseDto {
    private LocalTime time;
    private LocalDate date;
    private String movieName;
    private String theaterName;
    private String address;
    private String bookedSeats;
    private Long totalPrice;
}
