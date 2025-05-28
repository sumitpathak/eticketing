package com.entbooking.eticketing.dto.request;

import lombok.Data;

@Data
public class ShowSeatEntryDto {
    private Long showId;
    private Long priceOfPremiumSeat;
    private Long priceOfClassicSeat;
}
