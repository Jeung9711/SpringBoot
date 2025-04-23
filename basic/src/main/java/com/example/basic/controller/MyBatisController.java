package com.example.basic.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.basic.controller.model.Demo;
import com.example.basic.mapper.DemoMapper;

@RestController
public class MyBatisController {
    @Autowired DemoMapper demoMapper;
    
    @GetMapping("/mybatis/demo1")
    public List<Map<String, Object>> demo1() {
        List<Map<String, Object>> list = demoMapper.select1();
        return list;
    }

    @GetMapping("/mybatis/demo2")
    public List<Demo> demo2() {
        List<Demo> list = demoMapper.select2();
        return list;
    }

    @GetMapping("/mybatis/add1")
    public String add1(@RequestParam int seq, @RequestParam String user) {
        int result = demoMapper.insert1(seq, user);
        return "성공";
    }

    @GetMapping("/mybatis/add2")
    public String add2(@ModelAttribute Demo demo) {
        int result = demoMapper.insert2(demo);
        return "성공";
    }

    @GetMapping("/mybatis/remove")
    public String remove(@RequestParam int seq) {
        int result = demoMapper.delete(seq);
        return "성공";
    }
    

}