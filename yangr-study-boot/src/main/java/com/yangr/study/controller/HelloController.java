package com.yangr.study.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/sayHello/{word}")
    public String sayHello(@PathVariable("word") String word) {
        return "hello " + word + "!";
    }

}
