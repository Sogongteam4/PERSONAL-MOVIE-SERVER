package kgu.softwareEG.personalMovie.domain.user.dto;

import lombok.Builder;

@Builder
public record GetIsSurveyedResponseDto(
        Boolean isSurveyed
) {
    public static GetIsSurveyedResponseDto of(boolean isSurveyed) {
        return GetIsSurveyedResponseDto.builder()
                .isSurveyed(isSurveyed).build();
    }
}
