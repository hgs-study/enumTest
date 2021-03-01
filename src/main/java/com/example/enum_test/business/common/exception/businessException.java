package com.example.enum_test.business.common.exception;

import com.example.enum_test.business.enumTest.enumerated.errorCode;
import lombok.Getter;

public class businessException extends RuntimeException {

    @Getter
    private errorCode errorCode;

    public businessException(errorCode code){
        this.errorCode = code;
    }
}
