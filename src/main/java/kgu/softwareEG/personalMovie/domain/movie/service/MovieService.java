package kgu.softwareEG.personalMovie.domain.movie.service;

import kgu.softwareEG.personalMovie.domain.genre.repository.GenreRepository;
import kgu.softwareEG.personalMovie.domain.movie.dto.RecommendationDto;
import kgu.softwareEG.personalMovie.domain.movie.dto.response.GetMovieInfoResponseDto;
import kgu.softwareEG.personalMovie.domain.movie.entity.Movie;
import kgu.softwareEG.personalMovie.domain.movie.entity.MovieGenre;
import kgu.softwareEG.personalMovie.domain.movie.repository.MovieGenreRepository;
import kgu.softwareEG.personalMovie.domain.movie.repository.MovieRepository;
import kgu.softwareEG.personalMovie.global.error.ErrorCode;
import kgu.softwareEG.personalMovie.global.error.exception.EntityNotFoundException;
import kgu.softwareEG.personalMovie.global.util.YoutubeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.HashMap;
import java.util.*;
import java.util.stream.Collectors;

import static kgu.softwareEG.personalMovie.global.error.ErrorCode.MOVIE_NOT_FOUND;
import static kgu.softwareEG.personalMovie.global.util.YoutubeUtil.searchWord;

@Service
@RequiredArgsConstructor
@Transactional
public class MovieService {
    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final YoutubeUtil youtubeUtil;

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

    public GetMovieInfoResponseDto getMovieInfo(Long movieId) throws IOException {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new EntityNotFoundException(MOVIE_NOT_FOUND));
        if (movie.getTrailerUri() == null) {
            String trailer = youtubeUtil.searchVideo(movie.getTitle() + searchWord);
            movie.addTrailer(trailer);
        }
        return GetMovieInfoResponseDto.of(movie);
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
