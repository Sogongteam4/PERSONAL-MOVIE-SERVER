package kgu.softwareEG.personalMovie.domain.survey.service;

import kgu.softwareEG.personalMovie.domain.survey.dto.response.GetQuestionAndChoicesResponseDto;
import kgu.softwareEG.personalMovie.domain.survey.entity.Choice;
import kgu.softwareEG.personalMovie.domain.survey.entity.Question;
import kgu.softwareEG.personalMovie.domain.survey.repository.ChoiceRepository;
import kgu.softwareEG.personalMovie.domain.survey.repository.QuestionRepository;
import kgu.softwareEG.personalMovie.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SurveyService {

    private final QuestionService questionService;
    private final UserRepository userRepository;
    private final ChoiceRepository choiceRepository;
    private final QuestionRepository questionRepository;

    @Transactional(readOnly = true)
    public GetQuestionAndChoicesResponseDto getQuestionAndChoices(Long questionId) {
        Question question = questionService.findQuestionById(questionId);
        List<Choice> choices = question.getChoices();
        return GetQuestionAndChoicesResponseDto.of(question,choices);
    }

}
