package kgu.softwareEG.personalMovie.domain.survey.dto.response;

import kgu.softwareEG.personalMovie.domain.survey.entity.Choice;
import kgu.softwareEG.personalMovie.domain.survey.entity.Question;
import lombok.Builder;

import java.util.List;
import java.util.stream.Collectors;

@Builder
public record GetQuestionAndChoicesResponseDto(
        String question,
        List<ChoicesResponseDto> choices
) {
    public static GetQuestionAndChoicesResponseDto of(Question question, List<Choice> choices) {
        return GetQuestionAndChoicesResponseDto.builder()
                .question(question.getContent())
                .choices(choices.stream().map(choice -> ChoicesResponseDto.of(choice)).collect(Collectors.toList()))
                .build();
    }
}
