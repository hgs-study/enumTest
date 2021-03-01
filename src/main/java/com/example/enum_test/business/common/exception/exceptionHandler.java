package com.example.enum_test.business.common.exception;

import com.example.enum_test.business.enumTest.enumerated.errorCode;
import com.example.enum_test.business.enumTest.form.errorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class exceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<errorResponse> IllegalArgumentException(IllegalArgumentException e){
        errorResponse response = errorResponse.of(errorCode.INVALID_REQUEST);
        response.setDetail(e.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
}
