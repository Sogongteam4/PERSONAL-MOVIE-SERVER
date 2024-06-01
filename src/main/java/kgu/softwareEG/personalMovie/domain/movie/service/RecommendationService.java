package kgu.softwareEG.personalMovie.domain.movie.service;

import kgu.softwareEG.personalMovie.domain.movie.dto.response.GetMovieInfoResponseDto;
import kgu.softwareEG.personalMovie.domain.movie.entity.Movie;
import kgu.softwareEG.personalMovie.domain.movie.repository.MovieRepository;
import kgu.softwareEG.personalMovie.global.util.ItemSimilarity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecommendationService {

    @Autowired
    private MovieRepository movieRepository;

    public List<GetMovieInfoResponseDto> recommendByType(String type) {
        List<Movie> movies = movieRepository.findByType_NameOrderByRateDesc(type);
        return movies.stream().map(GetMovieInfoResponseDto::of).collect(Collectors.toList());
    }

    public List<GetMovieInfoResponseDto> recommendByGenre(String genre) {
        List<Movie> movies = movieRepository.findByMovieGenres_Genre_NameOrderByRateDesc(genre);
        return movies.stream().map(GetMovieInfoResponseDto::of).collect(Collectors.toList());
    }

    public List<GetMovieInfoResponseDto> recommendByYear(int year) {
        List<Movie> movies = movieRepository.findByYearOrderByRateDesc(year);
        return movies.stream().map(GetMovieInfoResponseDto::of).collect(Collectors.toList());
    }

    public List<GetMovieInfoResponseDto> recommendByRating() {
        List<Movie> movies = movieRepository.findAllByOrderByRateDesc();
        return movies.stream().map(GetMovieInfoResponseDto::of).collect(Collectors.toList());
    }
}

