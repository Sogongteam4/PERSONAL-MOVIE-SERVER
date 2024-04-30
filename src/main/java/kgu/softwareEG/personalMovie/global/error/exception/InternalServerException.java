package kgu.softwareEG.personalMovie.global.error.exception;


import kgu.softwareEG.personalMovie.global.error.ErrorCode;

public class InternalServerException extends BusinessException {
    public InternalServerException(ErrorCode errorCode) {
        super(errorCode);
    }
}