package com.example.enum_test.business.enumTest.enumerated;

import lombok.Getter;

@Getter
public enum testEnum {

    KIM(1,"대표","ㄱ"),
    HYUN(2,"팀장","ㄴ"),
    LEE(3,"파트장","ㄷ"),
    CHOI(4,"팀원","ㄹ");

    private int num;
    private String position;
    private String code;

    testEnum(int num,String position,String code){
        this.num = num;
        this.position = position;
        this.code = code;
    }

}
