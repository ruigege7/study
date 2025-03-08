package com.yangr.study.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class TestController {



    @PostMapping("/dialog")
    public String dialog() {

        return "这是dialog2";

    }




}
