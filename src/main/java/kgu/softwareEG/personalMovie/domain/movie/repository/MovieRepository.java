package kgu.softwareEG.personalMovie.domain.movie.repository;

import kgu.softwareEG.personalMovie.domain.movie.entity.Movie;
import kgu.softwareEG.personalMovie.domain.movie.entity.MovieGenre;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByYear(int year);
    List<Movie> findByMovieGenres(List<MovieGenre> movieGenres);
    List<Movie> findByYearAndMovieGenres(int year, List<MovieGenre> movieGenres);

}
