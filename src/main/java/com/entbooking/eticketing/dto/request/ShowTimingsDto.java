package com.entbooking.eticketing.dto.request;

import lombok.Data;

import java.sql.Date;

@Data
public class ShowTimingsDto {
    private Date date;
    private Long theaterId;
    private Long movieId;
}
