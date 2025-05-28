package com.entbooking.eticketing.dto.request;

import lombok.Data;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ShowEntryDto {

    private String showStartTime;
    private String showDate;
    private Long theaterId;
    private Long movieId;
}
