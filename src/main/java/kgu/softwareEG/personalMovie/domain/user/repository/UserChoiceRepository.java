package kgu.softwareEG.personalMovie.domain.user.repository;

import kgu.softwareEG.personalMovie.domain.user.entity.UserChoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserChoiceRepository extends JpaRepository<UserChoice, Long> {
}
