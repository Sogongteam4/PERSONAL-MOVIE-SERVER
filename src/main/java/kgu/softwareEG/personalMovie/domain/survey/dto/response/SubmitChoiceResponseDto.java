package kgu.softwareEG.personalMovie.domain.survey.dto.response;

import kgu.softwareEG.personalMovie.domain.survey.entity.Question;
import kgu.softwareEG.personalMovie.domain.user.entity.UserChoice;
import lombok.Builder;

@Builder
public record SubmitChoiceResponseDto(
        Long UserChoiceId
) {
    public static SubmitChoiceResponseDto of(UserChoice userChoice) {
        return SubmitChoiceResponseDto.builder().UserChoiceId(userChoice.getId()).build();
    }
}
