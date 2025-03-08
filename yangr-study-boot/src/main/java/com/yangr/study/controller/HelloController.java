package com.yangr.study.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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

    @GetMapping("/dialog")
    public String dialog() {

        OkHttpClient client = new OkHttpClient();

        // 创建请求
        Request request = new Request.Builder()
                .url("https://localhost:8090/app/dialog")
                .build();
        String word = "";
        // 发送请求并获取响应
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                System.out.println(response.body().string());
                word = response.body().string();
            } else {
                System.out.println("请求失败: " + response.code());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return word;
    }


}
