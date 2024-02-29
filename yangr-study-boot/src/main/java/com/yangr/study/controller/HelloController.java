package com.yangr.study.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * hellocontroller.
 *
 * @author yangr
 * @date 2024-1-29
 */
@RestController
@RequestMapping("/hello")
public class HelloController {
    @Autowired
    StringRedisTemplate stringRedisTemplate;


    @GetMapping("/sayHello/{word}")
    public String sayHello(@PathVariable("word") String word, HttpServletRequest request) {
        stringRedisTemplate.opsForValue().set("hello", "world");
        HttpSession httpSession = request.getSession();
        System.out.println(stringRedisTemplate.opsForValue().get("hello"));
        return "hello " + word + "!";
    }

}
