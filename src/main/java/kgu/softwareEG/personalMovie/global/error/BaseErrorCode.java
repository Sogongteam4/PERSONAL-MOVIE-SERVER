package kgu.softwareEG.personalMovie.global.error;


import kgu.softwareEG.personalMovie.global.error.dto.ErrorResponseDto;
import kgu.softwareEG.personalMovie.global.error.dto.FieldErrorResponseDto;

public interface BaseErrorCode {
    ErrorResponseDto getErrorReason();
    FieldErrorResponseDto getFieldErrorReason(Object result);
}
