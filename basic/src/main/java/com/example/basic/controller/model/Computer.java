package com.example.basic.controller.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// 클래스 작성시 습관적으로 작성하기
@ToString @Setter @Getter
public class Computer {
    String cpu;
    int power;
    int ram;
    int ssd;
}
