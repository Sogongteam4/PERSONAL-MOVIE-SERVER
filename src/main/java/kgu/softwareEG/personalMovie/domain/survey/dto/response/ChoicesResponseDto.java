package kgu.softwareEG.personalMovie.domain.survey.dto.response;

import kgu.softwareEG.personalMovie.domain.survey.entity.Choice;
import lombok.Builder;

@Builder
public record ChoicesResponseDto(
        Long id,
        String content
) {
    public static ChoicesResponseDto of(Choice choice) {
        return ChoicesResponseDto.builder()
                .id(choice.getId())
                .content(choice.getContent()).build();
    }
}
