package com.entbooking.eticketing.service.impl;

import com.entbooking.eticketing.dto.request.UserEntryDto;
import com.entbooking.eticketing.dto.response.TicketResponseDto;
import com.entbooking.eticketing.entity.Ticket;
import com.entbooking.eticketing.entity.User;
import com.entbooking.eticketing.repository.UserRepository;
import com.entbooking.eticketing.service.UserService;
import com.entbooking.eticketing.transformers.TicketTransformer;
import com.entbooking.eticketing.transformers.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    public String addUser(UserEntryDto userEntryDto) throws RuntimeException {
        if (userRepository.findByEmailId(userEntryDto.getEmailId()) != null) {
            throw new RuntimeException("User Already Exists With Email");
        }
        User user = UserTransformer.userDtoToUser(userEntryDto);

        userRepository.save(user);
        return "User Saved Successfully";
    }

    public List<TicketResponseDto> allTickets(Long userId) throws RuntimeException {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            throw new RuntimeException("User Does Not Exists");
        }
        User user = userOpt.get();
        List<Ticket> ticketList = user.getTicketList();
        List<TicketResponseDto> ticketResponseDtos = new ArrayList<>();
        for (Ticket ticket : ticketList) {
            TicketResponseDto ticketResponseDto = TicketTransformer.returnTicket(ticket.getShow(), ticket);
            ticketResponseDtos.add(ticketResponseDto);
        }
        return ticketResponseDtos;
    }
}
