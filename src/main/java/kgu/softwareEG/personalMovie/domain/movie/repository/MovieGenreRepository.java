package kgu.softwareEG.personalMovie.domain.movie.repository;

import kgu.softwareEG.personalMovie.domain.movie.entity.MovieGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieGenreRepository extends JpaRepository<MovieGenre, Long> {
}
