package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.component.Game;
import com.example.demo.component.ImageUtil;
import com.github.javafaker.Faker;

// 스프링에서 코드로 어떤 설정을 할 때
@Configuration
public class BeanConfig {
    @Bean
    public String bean1() {
        return "bean1";
    }

    // 빈은 이름보다 자료형이 중요
    @Bean
    public Game game() {
        return new Game();
    }

    @Bean
    public Game game2() {
        return new Game();
    }

    @Bean
    public Faker faker() {
        return new Faker();
    }

    @Bean
    public ImageUtil image() {
        return new ImageUtil();
    }

}
