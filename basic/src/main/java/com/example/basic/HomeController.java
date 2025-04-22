package com.example.basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.basic.controller.model.Member;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HomeController {
    @Autowired ObjectMapper objectMapper;

    @GetMapping("/home")
    public String home(Model model) {
        // java 데이터 html로 넘기기
        Map<String,Object> map = new HashMap<>();
        map.put("id","abcd");
        map.put("pw","1234");
        String json = "";
        try {
            json = objectMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // (html에서 사용할 이름, map)
        model.addAttribute("data", json);
        return "home";
    }

    @GetMapping("/html/exam")
    public String htmlExam() {
        return "html/exam";
    }
    
    @ResponseBody
    @GetMapping("/json/exam")
    public Map<String,Object> jsonExam() {
        Map<String, Object> map = new HashMap<>();
        map.put("count", 2);

        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> m = new HashMap<>();
        m.put("name", "가");
        m.put("userId", "A");
        m.put("userPassword", "1");
        list.add(m);
        
        Map<String, String> m2 = new HashMap<>();
        m2.put("name", "나");
        m2.put("userId", "B");
        m2.put("userPassword", "2");
        list.add(m2);

        map.put("list", list);

        return map;
    }
    
}