package com.example.basic.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.basic.controller.model.Demo;

@Mapper
public interface DemoMapper {
    public List<Map<String, Object>> select1();
    public List<Demo> select2();
    public int insert1(int seq, String user);
    public int insert2(Demo demo);
    public int delete(int seq);
}

