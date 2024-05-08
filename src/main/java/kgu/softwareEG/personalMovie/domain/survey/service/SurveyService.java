package kgu.softwareEG.personalMovie.domain.survey.service;

import kgu.softwareEG.personalMovie.domain.survey.dto.request.SubmitChoiceRequestDto;
import kgu.softwareEG.personalMovie.domain.survey.dto.response.GetQuestionAndChoicesResponseDto;
import kgu.softwareEG.personalMovie.domain.survey.dto.response.SubmitChoiceResponseDto;
import kgu.softwareEG.personalMovie.domain.survey.entity.Choice;
import kgu.softwareEG.personalMovie.domain.survey.entity.Question;
import kgu.softwareEG.personalMovie.domain.survey.repository.ChoiceRepository;
import kgu.softwareEG.personalMovie.domain.survey.repository.QuestionRepository;
import kgu.softwareEG.personalMovie.domain.user.entity.User;
import kgu.softwareEG.personalMovie.domain.user.entity.UserChoice;
import kgu.softwareEG.personalMovie.domain.user.repository.UserChoiceRepository;
import kgu.softwareEG.personalMovie.domain.user.repository.UserRepository;
import kgu.softwareEG.personalMovie.global.error.ErrorCode;
import kgu.softwareEG.personalMovie.global.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static kgu.softwareEG.personalMovie.global.error.ErrorCode.CHOICE_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional
public class SurveyService {

    private final QuestionService questionService;
    private final UserRepository userRepository;
    private final ChoiceRepository choiceRepository;
    private final UserChoiceRepository userChoiceRepository;
    private final QuestionRepository questionRepository;

    @Transactional(readOnly = true)
    public GetQuestionAndChoicesResponseDto getQuestionAndChoices(Long questionId) {
        Question question = questionService.findQuestionById(questionId);
        List<Choice> choices = question.getChoices();
        return GetQuestionAndChoicesResponseDto.of(question,choices);
    }

    public SubmitChoiceResponseDto submitChoice(Long userId, SubmitChoiceRequestDto submitChoiceRequestDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException(ErrorCode.MEMBER_NOT_FOUND));
        Choice choice = choiceRepository.findById(submitChoiceRequestDto.choiceId()).orElseThrow(() -> new EntityNotFoundException(CHOICE_NOT_FOUND));
        UserChoice userChoice = UserChoice.builder()
                .user(user)
                .choice(choice).build();

        UserChoice savedUserChoice = userChoiceRepository.save(userChoice);

        return SubmitChoiceResponseDto.of(savedUserChoice);
    }
}
