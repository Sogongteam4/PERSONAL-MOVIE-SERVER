package kgu.softwareEG.personalMovie.domain.movie.controller;

import kgu.softwareEG.personalMovie.domain.movie.dto.response.GetMovieInfoResponseDto;
import kgu.softwareEG.personalMovie.domain.movie.entity.Movie;
import kgu.softwareEG.personalMovie.domain.movie.entity.MovieGenre;
import kgu.softwareEG.personalMovie.domain.movie.repository.MovieRepository;
import kgu.softwareEG.personalMovie.domain.movie.service.MovieService;
import kgu.softwareEG.personalMovie.global.common.SuccessResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/movies")
public class MovieController {
    private final MovieRepository movieRepository;
    private final MovieService movieService;

    @GetMapping
    public ResponseEntity retrieveMovies(@PageableDefault(size = 100) Pageable pageable) {
        return movieService.retrieveMovies(pageable);
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<SuccessResponse<?>> getMovieInfo(@PathVariable("movieId") Long movieId) throws IOException {
        GetMovieInfoResponseDto getMovieInfoResponseDto = movieService.getMovieInfo(movieId);
        return SuccessResponse.ok(getMovieInfoResponseDto);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Movie>> getMoviesByYearAndGenre(
            @RequestParam(value = "year", required = false) Integer year,
            @RequestParam(value = "genre", required = false) List<MovieGenre> genre
    ) {
        List<Movie> movies;
        if (year != null && genre != null) {
            movies = movieService.findByYearAndGenre(year, genre);
        } else if (year != null) {
            movies = movieService.findByYear(year);
        } else if (genre != null) {
            movies = movieService.findByGenre(genre);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

}
