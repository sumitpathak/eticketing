package com.entbooking.eticketing.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class TicketEntryDto {
    private Long showId;
    private Long userId;
    private Long theatreId;
    private List<String> requestSeats;
}
