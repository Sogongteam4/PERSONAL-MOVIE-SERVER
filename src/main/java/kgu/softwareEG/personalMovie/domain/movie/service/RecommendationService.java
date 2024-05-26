package kgu.softwareEG.personalMovie.domain.movie.service;

import kgu.softwareEG.personalMovie.domain.movie.entity.Movie;
import kgu.softwareEG.personalMovie.domain.movie.repository.MovieRepository;
import kgu.softwareEG.personalMovie.global.util.ItemSimilarity;

import java.util.*;
import java.util.stream.Collectors;

public class RecommendationService {
    private MovieRepository movieRepository;

    private Map<Long, Map<Long, Double>> getUserRatings() {
        return new HashMap<>();
    }

    public List<Movie> recommendMovies(Long userId) {
        Map<Long, Map<Long, Double>> userRatings = getUserRatings();
        Set<Long> ratedMovieIds = userRatings.getOrDefault(userId, Collections.emptyMap()).keySet();

        Map<Long, Map<Long, Double>> movieUserRatings = new HashMap<>();
        for (Map.Entry<Long, Map<Long, Double>> entry : userRatings.entrySet()) {
            for (Map.Entry<Long, Double> movieRating : entry.getValue().entrySet()) {
                movieUserRatings.computeIfAbsent(movieRating.getKey(), k -> new HashMap<>())
                        .put(entry.getKey(), movieRating.getValue());
            }
        }

        Map<Long, Double> movieSimilarityScores = new HashMap<>();
        for (Long movieId : ratedMovieIds) {
            Map<Long, Double> currentMovieRatings = movieUserRatings.get(movieId);
            for (Map.Entry<Long, Map<Long, Double>> entry : movieUserRatings.entrySet()) {
                if (ratedMovieIds.contains(entry.getKey())) {
                    continue;
                }
                double similarity = ItemSimilarity.cosineSimilarity(currentMovieRatings, entry.getValue());
                movieSimilarityScores.merge(entry.getKey(), similarity, Double::sum);
            }
        }

        List<Long> recommendedMovieIds = movieSimilarityScores.entrySet()
                .stream()
                .sorted(Map.Entry.<Long, Double>comparingByValue().reversed())
                .limit(10)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        return movieRepository.findAllById(recommendedMovieIds);
    }
}
