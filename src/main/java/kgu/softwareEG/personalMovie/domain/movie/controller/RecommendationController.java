package kgu.softwareEG.personalMovie.domain.movie.controller;

import kgu.softwareEG.personalMovie.domain.movie.dto.response.GetMovieInfoResponseDto;
import kgu.softwareEG.personalMovie.domain.movie.entity.Movie;
import kgu.softwareEG.personalMovie.domain.movie.repository.MovieRepository;
import kgu.softwareEG.personalMovie.domain.movie.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;
    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("/type")
    public List<GetMovieInfoResponseDto> recommendByType(@RequestParam String type) {
        if ("8".equals(type)) {
            return recommendByRating();
        }
        else {
            List<Movie> movies = movieRepository.findByType_NameOrderByRateDesc(type);
            return recommendationService.recommendByType(type);
        }
    }

    @GetMapping("/genre")
    public List<GetMovieInfoResponseDto> recommendByGenre(@RequestParam String genre) {
        return recommendationService.recommendByGenre(genre);
    }

    @GetMapping("/year")
    public List<GetMovieInfoResponseDto> recommendByYear(@RequestParam int year) {
        return recommendationService.recommendByYear(year);
    }

    @GetMapping("/rating")
    public List<GetMovieInfoResponseDto> recommendByRating() {
        return recommendationService.recommendByRating();
    }
}
