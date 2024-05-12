package kgu.softwareEG.personalMovie.domain.movie.controller;

import kgu.softwareEG.personalMovie.domain.movie.entity.Movie;
import kgu.softwareEG.personalMovie.domain.movie.repository.MovieRepository;
import kgu.softwareEG.personalMovie.domain.movie.service.MovieService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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



}
