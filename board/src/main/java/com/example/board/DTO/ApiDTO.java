package com.example.board.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ApiDTO<T> {
    private int status;
    private String message;
    private T data; // data의 실제 타입을 나중에 결정
}
