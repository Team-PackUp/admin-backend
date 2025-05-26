package com.packup.admin.auth.exception;


import com.packup.admin.common.exception.BaseException;
import com.packup.admin.common.exception.BaseExceptionType;

public class AuthException extends BaseException {

    private final AuthExceptionType exceptionType;

    public AuthException(AuthExceptionType exceptionType) {
        super(exceptionType);
        this.exceptionType = exceptionType;
    }


    @Override
    public BaseExceptionType exceptionType() {
        return exceptionType;
    }
}