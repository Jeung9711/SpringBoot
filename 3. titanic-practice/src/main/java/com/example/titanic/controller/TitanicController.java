package com.example.titanic.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TitanicController {
  // DB 사용을 위한 선언
  @Autowired JdbcTemplate jdbcTemplate;

  @GetMapping("/survived")
  public List<Map<String, Object>> survived(
    @RequestParam String value) {
    int v = Integer.parseInt(value);
    String sql = "select * from titanic where survived = "+v;
    List<Map<String,Object>> result = new ArrayList<>();
    result = jdbcTemplate.queryForList(sql);
    return result;
  }

  @GetMapping("/name")
  public List<Map<String, Object>> name(
    @RequestParam String name
  ) {
    String sql = "select * from titanic where name like '%"+name+"%'";
    List<Map<String, Object>> result = new ArrayList<>();
    result = jdbcTemplate.queryForList(sql);
    return result;
  }

}
