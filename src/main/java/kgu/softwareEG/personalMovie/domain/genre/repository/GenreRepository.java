package kgu.softwareEG.personalMovie.domain.genre.repository;

import kgu.softwareEG.personalMovie.domain.genre.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
}
