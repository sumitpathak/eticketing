package com.entbooking.eticketing.service;

import com.entbooking.eticketing.dto.request.ShowEntryDto;
import com.entbooking.eticketing.dto.request.ShowSeatEntryDto;
import com.entbooking.eticketing.dto.request.ShowTimingsDto;
import com.entbooking.eticketing.entity.Show;

import java.sql.Time;
import java.util.List;

public interface ShowService {
    public String addShow(ShowEntryDto showEntryDto) throws RuntimeException;
    public String associateShowSeats(ShowSeatEntryDto showSeatEntryDto) throws RuntimeException;
    public List<Time> showTimingsOnDate(ShowTimingsDto showTimingsDto);
    public String movieHavingMostShows();
}
