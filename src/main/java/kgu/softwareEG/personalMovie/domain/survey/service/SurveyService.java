package kgu.softwareEG.personalMovie.domain.survey.service;

import kgu.softwareEG.personalMovie.domain.survey.dto.response.GetQuestionAndChoicesResponseDto;
import kgu.softwareEG.personalMovie.domain.survey.entity.Choice;
import kgu.softwareEG.personalMovie.domain.survey.entity.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SurveyService {

    private final QuestionService questionService;
    public GetQuestionAndChoicesResponseDto getQuestionAndChoices(Long questionId) {
        Question question = questionService.findQuestionById(questionId);
        List<Choice> choices = question.getChoices();
        return GetQuestionAndChoicesResponseDto.of(question,choices);
    }
}
