package com.entbooking.eticketing.utility;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component
public class DateAndTimeFormatter {

    public static LocalTime timeFormat(String time){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return LocalTime.from(formatter.parse(time));
    }

    public static LocalDate dateFormat(String date){
        //dd/MM/yyyy
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.from(formatter.parse(date));
    }
}
