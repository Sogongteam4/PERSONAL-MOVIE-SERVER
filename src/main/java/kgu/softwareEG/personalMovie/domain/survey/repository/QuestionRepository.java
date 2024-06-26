package kgu.softwareEG.personalMovie.domain.survey.repository;

import kgu.softwareEG.personalMovie.domain.survey.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
