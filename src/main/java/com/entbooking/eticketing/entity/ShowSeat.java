package com.entbooking.eticketing.entity;

import com.entbooking.eticketing.enums.SeatType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "SHOW_SEATS")
@Data
public class ShowSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String seatNo;

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    private Long price;
    private Boolean isAvailable;
    private Boolean isFoodContains;

    @ManyToOne
    @JoinColumn
    private Show show;
}
