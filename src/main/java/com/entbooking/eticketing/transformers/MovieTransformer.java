package com.entbooking.eticketing.transformers;


import com.entbooking.eticketing.dto.request.MovieEntryDto;
import com.entbooking.eticketing.entity.Movie;

public class MovieTransformer {

    public static Movie movieDtoToMovie(MovieEntryDto movieEntryDto) {

        return Movie.builder()
                .movieName(movieEntryDto.getMovieName())
                .duration(movieEntryDto.getDuration())
                .genre(movieEntryDto.getGenre())
                .language(movieEntryDto.getLanguage())
                .releaseDate(movieEntryDto.getReleaseDate())
                .rating(movieEntryDto.getRating())
                .build();
    }
}
