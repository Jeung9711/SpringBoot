package com.example.request.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.request.model.User;

@RestController
public class RequestController {
  User user = new User();
  {
    user.setAge(0);
    user.setName("");
  }

  @GetMapping("/main")
  public String main() {
    return "main";
  }

  @CrossOrigin(origins = "http://localhost:5500")  
  @GetMapping("/query")
  public String getQuery() {
    return "GET Query Received: " + user.getName() + ", " + user.getAge();
  }

  @PostMapping("/form")
  public String postForm() {
    return "POST FormData Received: " + user.getName() + ", " + user.getAge();
  }

  @PostMapping("/json")
  public String postJson() {
    return "POST JSON Received: " + user.getName() + ", " + user.getAge();
  }

  @CrossOrigin(origins = "http://localhost:5500") // 포트 다른 클라이언트에서만 허용
  @PostMapping("/cross-json")
  public String postCrossJson() {
    return "CORS JSON Received: " + user.getName() + ", " + user.getAge();
  }
}
