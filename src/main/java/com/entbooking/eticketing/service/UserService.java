package com.entbooking.eticketing.service;

import com.entbooking.eticketing.dto.request.UserEntryDto;
import com.entbooking.eticketing.dto.response.TicketResponseDto;

import java.util.List;

public interface UserService {

    String addUser(UserEntryDto userEntryDto);
    public List<TicketResponseDto> allTickets(Long userId) throws RuntimeException;
}
