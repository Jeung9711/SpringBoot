package com.example.basic.controller;

import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class MethodController {
    // get 방식으로 전달
    @GetMapping("req/get")
    public String get(
        HttpSession session
        ) {
            session.setAttribute("abcd","1234");
        return "GET";
    }
    
    // post 방식으로 전달
    @PostMapping("req/post")
    public String post() {
        return "POST";
    }
    
}
