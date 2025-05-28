package com.entbooking.eticketing.service.impl;

import com.entbooking.eticketing.dto.request.TicketEntryDto;
import com.entbooking.eticketing.dto.response.TicketResponseDto;
import com.entbooking.eticketing.entity.*;
import com.entbooking.eticketing.repository.*;
import com.entbooking.eticketing.service.TicketService;
import com.entbooking.eticketing.transformers.TicketTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TheaterRepository theaterRepository;

    public TicketResponseDto ticketBooking(TicketEntryDto ticketEntryDto) throws RuntimeException {
        // check user present
        Optional<Show> showOpt = showRepository.findById(ticketEntryDto.getShowId());
        if (showOpt.isEmpty()) {
            throw new RuntimeException("Show Does Not Exists");
        }

        Optional<Theater> theaterOpt = theaterRepository.findById(ticketEntryDto.getTheatreId());

        //check show present
        Optional<User> userOpt = userRepository.findById(ticketEntryDto.getUserId());
        if (userOpt.isEmpty()) {
            throw new RuntimeException("User Does Not Exists");
        }

        User user = userOpt.get();
        Show show = showOpt.get();

        //check a requested seat available
        Boolean isSeatAvailable = isSeatAvailable(show.getShowSeatList(), ticketEntryDto.getRequestSeats());
        if (!isSeatAvailable) {
            throw new RuntimeException("Requested Seat Are Not Available");
        }

        // count price
        Long getPriceAndAssignSeats =
                getPriceAndAssignSeats(user, show.getTime(), show.getShowSeatList(), ticketEntryDto.getRequestSeats());

        // change list to string
        String seats = listToString(ticketEntryDto.getRequestSeats());

        // create ticket entity and set all attributes
        Ticket ticket = new Ticket();
        ticket.setTotalTicketsPrice(getPriceAndAssignSeats);
        ticket.setBookedSeats(seats);

        // setting foreign key variables
        ticket.setUser(user);
        ticket.setShow(show);

        ticket = ticketRepository.save(ticket);

        user.getTicketList().add(ticket);
        show.getTicketList().add(ticket);
        userRepository.save(user);
        showRepository.save(show);


        // build Ticket Response Dto
        return TicketTransformer.returnTicket(show, ticket);
    }


    private Boolean isSeatAvailable(List<ShowSeat> showSeatList, List<String> requestSeats) {
        for (ShowSeat showSeat : showSeatList) {
            String seatNo = showSeat.getSeatNo();
            if (requestSeats.contains(seatNo)) {
                if (!showSeat.getIsAvailable()) {
                    return false;
                }
            }
        }
        return true;
    }

    private Long getPriceAndAssignSeats(User user, LocalTime time, List<ShowSeat> showSeatList, List<String> requestSeats) {
        Long totalAmount = 0L;
        for (ShowSeat showSeat : showSeatList) {
            if (requestSeats.contains(showSeat.getSeatNo())) {
                totalAmount += showSeat.getPrice();
                showSeat.setIsAvailable(Boolean.FALSE);
            }
        }
        //Applying 50% discount for 3rd ticket
        if (user.getTicketList().size() == 2) {
            totalAmount -= ((totalAmount * 50) / 100);
        } else if (isAfternoon(time)) {
            totalAmount -= ((totalAmount * 20) / 100);
        }
        return totalAmount;
    }

    private  boolean isAfternoon(LocalTime time) {
        LocalTime afternoonStart = LocalTime.of(12, 0, 0); // 12:00 PM
        LocalTime afternoonEnd = LocalTime.of(17, 0, 0); // 5:00 PM
        return !time.isBefore(afternoonStart) && time.isBefore(afternoonEnd);
    }


    private String listToString(List<String> requestSeats) {
        StringBuilder sb = new StringBuilder();
        for (String s : requestSeats) {
            sb.append(s).append(",");
        }
        return sb.toString();
    }

}
