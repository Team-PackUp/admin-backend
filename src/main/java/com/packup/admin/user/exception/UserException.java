package com.packup.admin.user.exception;

import com.packup.admin.common.exception.BaseException;
import com.packup.admin.common.exception.BaseExceptionType;

public class UserException extends BaseException {

    private final UserExceptionType exceptionType;

    public UserException(UserExceptionType exceptionType) {
        super(exceptionType);
        this.exceptionType = exceptionType;
    }

    @Override
    public BaseExceptionType exceptionType() {
        return exceptionType;
    }
}