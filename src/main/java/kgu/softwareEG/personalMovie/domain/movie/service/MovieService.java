package kgu.softwareEG.personalMovie.domain.movie.service;

import kgu.softwareEG.personalMovie.domain.genre.repository.GenreRepository;
import kgu.softwareEG.personalMovie.domain.movie.dto.RecommendationDto;
import kgu.softwareEG.personalMovie.domain.movie.entity.Movie;
import kgu.softwareEG.personalMovie.domain.movie.entity.MovieGenre;
import kgu.softwareEG.personalMovie.domain.movie.repository.MovieGenreRepository;
import kgu.softwareEG.personalMovie.domain.movie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;

    public ResponseEntity retrieveMovies(Pageable pageable) {
        Page<Movie> moviesPage = movieRepository.findAll(pageable);
        return new ResponseEntity(moviesPage, HttpStatus.OK);
    }

    public HashMap<MovieGenre, Integer> sortByValue(HashMap<MovieGenre, Integer> raw) {
        return raw.entrySet()
                .stream()
                .sorted((i1, i2) -> i1.getValue().compareTo(i2.getValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e2, LinkedHashMap::new
                ));
    }

    /*public HashMap<MovieGenre, Integer> getPickedGenres(RecommendationDto recommendationDto) {
        HashMap<MovieGenre, Integer> pickedGenres = new HashMap<>();
        recommendationDto.getPickedGenres().forEach(
                movieData -> {
                    Movie movie = movieRepository.findById(movieData.getMovieId()).orElseThrow(
                            () -> new IllegalStateException("Cannot find movies with given id: " + movieData.getMovieId().toString()));

                    Set<MovieGenre> genreList = movie.getGenres();
                    for(MovieGenre movieGenre : genreList) {
                        Integer count = pickedGenres.getOrDefault(genreList, 0);
                        pickedGenres.put(genreList, count);
                    }
                }
        );
        return pickedGenres;
    }*/
}
