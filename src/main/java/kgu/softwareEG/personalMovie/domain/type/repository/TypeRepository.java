package kgu.softwareEG.personalMovie.domain.type.repository;

import kgu.softwareEG.personalMovie.domain.type.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {
}
