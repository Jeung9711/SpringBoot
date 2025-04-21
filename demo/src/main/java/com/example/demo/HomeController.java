package com.example.demo;

import java.io.IOException;
import java.sql.JDBCType;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.component.Bean3;
import com.example.demo.component.Game;
import com.example.demo.component.ImageUtil;
import com.github.javafaker.Faker;

import lombok.extern.slf4j.Slf4j;

@Slf4j  // 로그와 관련된 클래스, lombok 설정 후 사용

// 아무도 객체 생성을 하지 않았으나 사용됨 -> IoC
// homeController가 어디선가 사용됨 -> DI
// 작동법을 명확히 모르는 코드를 외워서 사용 -> 프레임워크 ex)react
@Controller
public class HomeController {
    // bean 생성
    @Autowired String bean1;   //DI
    @Autowired Game game;
    @Autowired Bean3 bean3;
    @Autowired Faker faker;
    @Autowired JdbcTemplate jdbcTemplate;
    @Autowired ImageUtil image;
    // Controller 안에는 00Methode가 꼭 있어야함 = 컨트롤러 메소드
    // get 방식으로 요청이 왔을때 연결해주는 메소드
    // html 없이 사용하려면 위나 아래에 @ResponseBoby 작성
    @GetMapping("/home")
    public String home() {
        System.out.println(bean1);
        System.out.println(game.play());
        System.out.println(bean3.run());

        String name = faker.name().fullName();
        String job = faker.job().title();
        String address = faker.address().fullAddress();
        System.out.println(
        String.format("이름: %s\n직업: %s\n주소: %s", name, job, address));

        log.debug("debug");  // 기본 레벨이 info 이상이라서
        log.error("error!!");
        log.info("info!");

        return "home";
    }

    @GetMapping("/home2")
    @ResponseBody
    public List<Map<String, Object>> home2() {
        String sql = "select * from emp";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        
        return list;
    }

    @GetMapping("/img")
    @ResponseBody
    public ImageUtil img() throws IOException {
        String url = "http://ggoreb.com/images/luffy.jpg";
        image.save(url);

        return null;
    }
}
    
