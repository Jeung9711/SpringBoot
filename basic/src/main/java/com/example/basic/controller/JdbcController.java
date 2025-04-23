package com.example.basic.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import com.example.basic.DAO.DemoDAO;
import com.example.basic.controller.model.Demo;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@RestController
public class JdbcController {
    @Autowired DemoDAO demoDAO;

    @GetMapping("/jdbc/demo1")
    public List<Map<String, Object>> demo1() {
        List<Map<String, Object>> list = demoDAO.select1();
        return list;
    }

    @GetMapping("/jdbc/demo2")
    public List<Demo> demo2() {
        List<Demo> list = demoDAO.select2();
        return list;
    }

    @ResponseBody
    @GetMapping("/jdbc/add")
    public String add(@ModelAttribute Demo demo) {
        int result = 0;
        String msg = null;

        try {
            result = demoDAO.insert(demo);
        } catch (DataAccessException e) {
            result = 0;
            msg = e.getMessage();
        }

        if(result == 0) return "실패: "+msg;

        return "성공";
    }

    @GetMapping("/jdbc/remove")
    public String remove(@ModelAttribute Demo demo) {
        int result = 0;
        String msg = null;
        try{
            result = demoDAO.delete(demo);
        }catch (DataAccessException e) {
            result = 0;
            msg = e.getMessage();
        }

        if(result == 0) return "fail : "+msg;

        return "success";
    }
    
    

}

