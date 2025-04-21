package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// json으로 출력하려면 RestController 사용
@RestController
public class MainController {
    @GetMapping("/board")
    public String boad(
        @RequestParam String num,
        @RequestParam String name) {
        return "게시물 번호는 => " + num + " 이름은 => " + name;
    }
}
