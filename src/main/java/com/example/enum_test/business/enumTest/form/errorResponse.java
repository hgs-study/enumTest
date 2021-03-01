package com.example.enum_test.business.enumTest.form;

import com.example.enum_test.business.enumTest.enumerated.errorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class errorResponse {

    private int status;
    private String code;
    private String message;
    private String detail;


    public errorResponse(errorCode code){
        this.status= code.getStatus();
        this.code = code.getCode();
        this.message= code.getMessage();
        this.detail = code.getDetail();
    }

    public static errorResponse of(errorCode code){
        return new errorResponse(code);
    }
}
