package com.example.board.DTO;

import java.util.Optional;

import com.example.board.entity.Board;
import com.example.board.entity.User;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Data
public class BoardDTO {
    private Long id;
    private String title;
    private String content;
    private Long userId;
    private String userName;

    public static BoardDTO toDto(Board board) {
        BoardDTO dto = new BoardDTO();
        dto.setId(board.getId());
        dto.setTitle(board.getTitle());
        dto.setContent(board.getContent());
        User user = board.getUser();
        dto.setUserId(user.getId());
        dto.setUserName(user.getName());
        return dto;
    }

    public static BoardDTO toDto(Optional<Board> board) {
        if(board.isEmpty()) return null;
        return toDto(board.get()); // Optional을 벗긴 객체를 todto에 넣어 반환
    }

}
