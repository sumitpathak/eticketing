package com.entbooking.eticketing.transformers;


import com.entbooking.eticketing.dto.request.ShowEntryDto;
import com.entbooking.eticketing.entity.Show;
import com.entbooking.eticketing.utility.DateAndTimeFormatter;

public class ShowTransformer {

    public static Show showDtoToShow(ShowEntryDto showEntryDto) {

        return Show.builder()
                .time(DateAndTimeFormatter.timeFormat(showEntryDto.getShowStartTime()))
                .date(DateAndTimeFormatter.dateFormat(showEntryDto.getShowDate()))
                .build();
    }
}
