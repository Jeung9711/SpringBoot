package com.example.basic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.basic.controller.model.Computer;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class RequestController {
    @GetMapping("req/http")
    public String http(HttpServletRequest request,
    @RequestParam(defaultValue = "1") Integer page,
    @RequestParam(required = false) String search) {
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie: cookies) {
            String id = cookie.getValue();
            System.out.println(id);
        }
        String name = request.getParameter("name");
        String pageNum = request.getParameter("pageNum");
        return name + ", " + pageNum;
    }

    @GetMapping("req/path/{path1}/{path2}")
    public String path(
        @PathVariable String path1,
        @PathVariable String path2) {
        return path1 + ", " + path2;
    }

    @GetMapping("req/model")
    public String model(
        // 생략하면 자동으로 @ModelAttribute
        @ModelAttribute Computer computer, 
        int ssd) {
        return computer.toString();
    }

}
