package com.packup.admin.common.exception;

import com.packup.admin.auth.exception.AuthException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(AuthException.class)
//    public ResponseEntity<?> handleAuthException(AuthException ex) {
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                .body(Map.of("message", ex.getMessage()));
//    }


}

