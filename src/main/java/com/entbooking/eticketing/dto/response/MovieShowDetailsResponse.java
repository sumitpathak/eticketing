package com.entbooking.eticketing.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieShowDetailsResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = -3091411943800160293L;

    private Long theaterId;
    private String theaterName;
    private String movieName;
    private String city;
    private String showDate;
    private List<String> showTime = new ArrayList<>();
}
