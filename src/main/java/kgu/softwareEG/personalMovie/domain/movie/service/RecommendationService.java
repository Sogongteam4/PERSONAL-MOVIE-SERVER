package kgu.softwareEG.personalMovie.domain.movie.service;

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

    private Map<Long, Map<Long, Double>> getUserRatings() {
        List<Movie> movies = movieRepository.findAll();
        Map<Long, Map<Long, Double>> movieRatings = new HashMap<>();

        for (Movie movie : movies) {
            movieRatings.computeIfAbsent(movie.getId(), k -> new HashMap<>())
                    .put(movie.getId(), (double) movie.getRate());
        }

        return movieRatings;
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

    public List<Movie> recommendMoviesByType(Long userId, Long typeId) {
        Map<Long, Map<Long, Double>> userRatings = getUserRatings();
        Set<Long> ratedMovieIds = userRatings.getOrDefault(userId, Collections.emptyMap()).keySet();

        List<Movie> typeMovies = movieRepository.findByTypeId(typeId);
        Set<Long> typeMovieIds = typeMovies.stream().map(Movie::getId).collect(Collectors.toSet());

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
                if (ratedMovieIds.contains(entry.getKey()) || !typeMovieIds.contains(entry.getKey())) {
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

