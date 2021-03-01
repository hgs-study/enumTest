package com.example.enum_test.business.enumTest.enumerated;

import lombok.Getter;

@Getter
public enum errorCode {

    //COMMON
    INVALID_REQUEST(400,"C001","잘못된 요청입니다."),
    RESOURCE_NOT_FOUND(404,"COO2","해당 리소스를 찾을 수 없습니다."),
    FORBIDDEN(403,"C003","해당 권한이 없습니다."),
    SERVER_ERROR(500,"COO4","서버에서의 오류입니다.");

    private int status;
    private String code;
    private String message;
    private String detail;

    errorCode(int status, String code, String message){
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
