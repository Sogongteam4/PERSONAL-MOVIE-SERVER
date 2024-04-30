package kgu.softwareEG.personalMovie.global.error.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Builder
@Getter
public class ErrorResponseDto {
    private HttpStatus status;
    private int code;
    private String message;
}
