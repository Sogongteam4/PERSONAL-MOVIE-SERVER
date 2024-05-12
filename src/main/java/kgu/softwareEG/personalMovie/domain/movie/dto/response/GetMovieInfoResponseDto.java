package kgu.softwareEG.personalMovie.domain.movie.dto.response;

import kgu.softwareEG.personalMovie.domain.movie.entity.Movie;
import lombok.Builder;

import java.util.List;
import java.util.stream.Collectors;

@Builder
public record GetMovieInfoResponseDto(
        String name,
        int releaseYear,
        String trailerUri,
        float rate,
        List<String> genres
) {
    public static GetMovieInfoResponseDto of (Movie movie) {
        String cleanedName = movie.getTitle().replaceAll("\\s*\\(\\d+\\)$", "");

        return GetMovieInfoResponseDto.builder()
                .name(cleanedName)
                .releaseYear(movie.getYear())
                .trailerUri(movie.getTrailerUri())
                .genres(movie.getMovieGenres().stream()
                        .map(movieGenre -> movieGenre.getGenre().getName())
                        .collect(Collectors.toList()))
                .rate(movie.getRate())
                .build();
    }
}
