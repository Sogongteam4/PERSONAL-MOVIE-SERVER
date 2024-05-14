package kgu.softwareEG.personalMovie.domain.type.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record GetUserTypeRequestDto(

        @NotNull @Size(min = 4, max = 4)
        List<Long> choices
) {
}
