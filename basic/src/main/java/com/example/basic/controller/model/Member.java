package com.example.basic.controller.model;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class Member {
    String name;

    public Member () {}
    public Member(String name) {
        this.name = name;
    }
}
