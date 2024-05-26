package kgu.softwareEG.personalMovie.domain.movie.controller;

import kgu.softwareEG.personalMovie.domain.movie.entity.Movie;
import kgu.softwareEG.personalMovie.domain.movie.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

public class RecommendationController {
    @Autowired
    private RecommendationService recommendationService;

    @GetMapping("api/{userId}")
    public List<Movie> recommendMovies(@PathVariable Long userId) {
        return recommendationService.recommendMovies(userId);
    }

}
