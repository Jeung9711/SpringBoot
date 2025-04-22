package com.example.basic.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.basic.controller.model.Member;

@Controller
public class JsonController {

    @ResponseBody
    @GetMapping("json1/string")
    public String json() {
        return "json/string";
    }

    @GetMapping("json1/map")
    @ResponseBody
    public Map<String, Object> jsonMap(Map<String, Object> map) {
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("key1", "value");
        map2.put("key2", 2324);
        map2.put("key3", true);
        return map2;
    }

    @GetMapping("json1/map2")
    @ResponseBody
    // content-type을 변경할 수 있음
    public ResponseEntity jsonMap2(Map<String, Object> map) {
        // Map<String, Object> map2 = new HashMap<String, Object>();
        // map2.put("key1", "value");
        // map2.put("key2", 2324);
        // map2.put("key3", true);
        return ResponseEntity
                            .ok()
                            .contentType(MediaType.IMAGE_GIF)
                            .body("123");
    }

    // DIO : 데이터 전송을 위한 객체
    @GetMapping("json1/object")
    @ResponseBody
    public Member jsonObject() {
        Member member = new Member();
        member.setName("kim");
        return member;
    }

    @GetMapping("json1/list")
    @ResponseBody
    public List<Member> jsonList() {
        List<Member> list = new ArrayList<>();
        list.add(new Member("1"));
        list.add(new Member("2"));
        list.add(new Member("3"));
        list.add(new Member("4"));
        list.add(new Member("5"));
        return list;
    }

}
