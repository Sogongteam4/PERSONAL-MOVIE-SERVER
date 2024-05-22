package kgu.softwareEG.personalMovie.domain.survey.service;

import kgu.softwareEG.personalMovie.domain.survey.entity.Choice;
import kgu.softwareEG.personalMovie.domain.survey.entity.Question;
import kgu.softwareEG.personalMovie.domain.survey.repository.QuestionRepository;
import kgu.softwareEG.personalMovie.global.error.ErrorCode;
import kgu.softwareEG.personalMovie.global.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public Question findQuestionById(Long questionId) {
        return questionRepository.findById(questionId)
                .orElseThrow(()-> new EntityNotFoundException(ErrorCode.ENTITY_NOT_FOUND));
    }

}
