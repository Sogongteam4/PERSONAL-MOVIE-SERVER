package kgu.softwareEG.personalMovie.global.error.exception;


import kgu.softwareEG.personalMovie.global.error.ErrorCode;

public class ConflictException extends BusinessException {
    public ConflictException() {
        super(ErrorCode.CONFLICT);
    }

    public ConflictException(ErrorCode errorCode) {
        super(errorCode);
    }
}