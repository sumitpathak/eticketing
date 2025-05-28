package com.entbooking.eticketing.service;

import com.entbooking.eticketing.dto.request.TicketEntryDto;
import com.entbooking.eticketing.dto.response.TicketResponseDto;

public interface TicketService {
    public TicketResponseDto ticketBooking(TicketEntryDto ticketEntryDto) throws RuntimeException;

}
