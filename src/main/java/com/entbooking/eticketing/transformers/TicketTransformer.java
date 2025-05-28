package com.entbooking.eticketing.transformers;


import com.entbooking.eticketing.dto.response.TicketResponseDto;
import com.entbooking.eticketing.entity.Show;
import com.entbooking.eticketing.entity.Ticket;

public class TicketTransformer {

    public static TicketResponseDto returnTicket(Show show, Ticket ticket) {

        return TicketResponseDto.builder()
                .bookedSeats(ticket.getBookedSeats())
                .address(show.getTheater().getAddress())
                .theaterName(show.getTheater().getName())
                .movieName(show.getMovie().getMovieName())
                .date(show.getDate())
                .time(show.getTime())
                .totalPrice(ticket.getTotalTicketsPrice())
                .build();
    }
}
