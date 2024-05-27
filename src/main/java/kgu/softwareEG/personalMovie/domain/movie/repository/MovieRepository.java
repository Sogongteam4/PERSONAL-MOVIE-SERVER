package kgu.softwareEG.personalMovie.domain.movie.repository;

import kgu.softwareEG.personalMovie.domain.movie.entity.Movie;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByTypeId(Long typeId);

}
