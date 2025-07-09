package com.packup.admin.guide.exception;

import com.packup.admin.common.exception.BaseException;
import com.packup.admin.common.exception.BaseExceptionType;
import com.packup.admin.user.exception.UserExceptionType;

public class GuideException extends BaseException {

    private final GuideExceptionType exceptionType;

    public GuideException(GuideExceptionType exceptionType) {
        super(exceptionType);
        this.exceptionType = exceptionType;
    }

    @Override
    public BaseExceptionType exceptionType() {
        return exceptionType;
    }
}