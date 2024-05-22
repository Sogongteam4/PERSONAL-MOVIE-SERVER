package kgu.softwareEG.personalMovie.domain.type.dto.response;

import kgu.softwareEG.personalMovie.domain.type.entity.Type;
import lombok.Builder;

import java.util.List;
import java.util.stream.Collectors;

@Builder
public record GetUserTypeResponseDto(
        String type,
        String imgUri,
        List<Long> movieIds
) {
    public static GetUserTypeResponseDto of(Type type) {
        return GetUserTypeResponseDto.builder()
                .type(type.getName())
                .imgUri(type.getImgUri())
                .movieIds(type.getMovies().stream().map(movie -> movie.getId()).collect(Collectors.toList()))
                .build();
    }
}
