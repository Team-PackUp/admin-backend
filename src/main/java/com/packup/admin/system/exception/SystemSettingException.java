package com.packup.admin.system.exception;

import com.packup.admin.common.exception.BaseException;
import com.packup.admin.common.exception.BaseExceptionType;

public class SystemSettingException extends BaseException {
    private final SystemSettingExceptionType exceptionType;

    public SystemSettingException(SystemSettingExceptionType exceptionType) {
        super(exceptionType);
        this.exceptionType = exceptionType;
    }


    @Override
    public BaseExceptionType exceptionType() {
        return exceptionType;
    }
}
