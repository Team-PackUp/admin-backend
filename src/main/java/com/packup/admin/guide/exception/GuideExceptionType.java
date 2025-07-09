package com.packup.admin.guide.exception;


import com.packup.admin.common.exception.BaseExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
public enum GuideExceptionType implements BaseExceptionType {

    NOT_FOUND_APPLICATION(NOT_FOUND, "지원서를 찾을 수 없습니다"),
    ALREADY_PROCESSED_APPLICATION(BAD_REQUEST, "이미 처리된 지원서입니다."),
    INVALID_REQUEST(BAD_REQUEST, "유효하지 않은 요청입니다."),
    ;

    private final HttpStatus httpStatus;
    private final String errorMessage;

    @Override
    public HttpStatus httpStatus() {
        return httpStatus;
    }

    @Override
    public String errorMessage() {
        return errorMessage;
    }
}
