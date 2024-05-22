package kgu.softwareEG.personalMovie.domain.user.dto.response;

import lombok.Builder;

@Builder
public record LoginResponseDto(
        String accessToken
) {
    public static LoginResponseDto of(String accessToken) {
        return LoginResponseDto.builder().accessToken(accessToken).build();
    }
}
