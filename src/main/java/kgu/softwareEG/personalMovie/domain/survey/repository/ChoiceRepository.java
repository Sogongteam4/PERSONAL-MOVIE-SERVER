package kgu.softwareEG.personalMovie.domain.survey.repository;

import kgu.softwareEG.personalMovie.domain.survey.entity.Choice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChoiceRepository extends JpaRepository<Choice, Long> {
}
