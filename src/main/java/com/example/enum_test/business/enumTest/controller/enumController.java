package com.example.enum_test.business.enumTest.controller;

import com.example.enum_test.business.enumTest.enumerated.testEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("testEnum")
public class enumController {

    @GetMapping("{name}")
    public String enumTest(@PathVariable String name){
        //return testEnum.valueOf(name).getPosition();
        return testEnum.valueOf(name).getPosition();
    }


    @GetMapping("/exceptionEnumTest")
    public void exceptionEnumTest(){
        throw new IllegalArgumentException("오류입니다.");
    }

}
