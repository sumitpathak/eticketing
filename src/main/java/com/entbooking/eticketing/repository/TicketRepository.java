package com.entbooking.eticketing.repository;

import com.entbooking.eticketing.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket,Long> {
}
