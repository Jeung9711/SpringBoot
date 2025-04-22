package com.example.basic.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@RestController
public class PracticeController {
    @ResponseBody
    @GetMapping("/req/data")
    public Map<String, Object> reqData(
        String area, Integer score
    ) {
        Map<String, Object> map = new HashMap<>();
        
        if(area != null) map.put("area",area);
        if(score != null) map.put("score", score);

        return map;
    }

    @ResponseBody
    @GetMapping("/req/data2")
    public Map<String, Object> reqData2(
        @RequestParam Map<String,Object> map
    ) {
        return map;
    }
    
}
