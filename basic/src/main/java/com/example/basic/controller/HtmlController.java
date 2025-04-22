package com.example.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HtmlController {
    @GetMapping("html/string")
    public String htmlString() {
        // templates/html/string.html 파일을 찾음
        return "html/string";
    }    
    
}
