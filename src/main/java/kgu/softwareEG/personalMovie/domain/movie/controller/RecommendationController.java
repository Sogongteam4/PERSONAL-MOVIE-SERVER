package kgu.softwareEG.personalMovie.domain.movie.controller;

import kgu.softwareEG.personalMovie.domain.movie.entity.Movie;
import kgu.softwareEG.personalMovie.domain.movie.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @GetMapping("/{userId}")
    public List<Movie> recommendMovies(@PathVariable Long userId) {
        return recommendationService.recommendMovies(userId);
    }

    @GetMapping("/{userId}/type/{typeId}")
    public List<Movie> recommendMoviesByType(@PathVariable Long userId, @PathVariable Long typeId) {
        return recommendationService.recommendMoviesByType(userId, typeId);
    }
}
