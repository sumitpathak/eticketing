package com.entbooking.eticketing.dto.request;


import com.entbooking.eticketing.enums.Genre;
import com.entbooking.eticketing.enums.Language;
import lombok.Data;

import java.sql.Date;

@Data
public class MovieEntryDto {
    private String movieName;
    private Long duration;
    private Double rating;
    private Date releaseDate;
    private Genre genre;
    private Language language;
}
