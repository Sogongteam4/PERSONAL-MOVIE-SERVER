package kgu.softwareEG.personalMovie.domain.survey.dto.request;

public record SubmitChoiceRequestDto(
        Long questionId,
        Long choiceId
) {
}