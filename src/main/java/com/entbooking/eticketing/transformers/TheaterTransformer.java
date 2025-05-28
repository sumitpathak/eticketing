package com.entbooking.eticketing.transformers;


import com.entbooking.eticketing.dto.request.TheaterEntryDto;
import com.entbooking.eticketing.entity.Theater;

public class TheaterTransformer {

    public static Theater theaterDtoToTheater(TheaterEntryDto entryDto) {
        return Theater.builder()
                .name(entryDto.getName())
                .address(entryDto.getAddress())
                .city(entryDto.getCity())
                .build();
    }
}
