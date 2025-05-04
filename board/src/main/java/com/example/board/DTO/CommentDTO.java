package com.example.board.DTO;

import java.time.LocalDateTime;
import java.util.Optional;

import com.example.board.entity.Board;
import com.example.board.entity.Comment;
import com.example.board.entity.User;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data @Setter @Getter
public class CommentDTO {
    private Long id;
    private String comment;
    private LocalDateTime date;
    private Long userId;
    private String userName;
    private Long boardId;

    public static CommentDTO toDto(Comment comment) {
        CommentDTO dto = new CommentDTO();
        dto.setId(comment.getId());
        dto.setComment(comment.getComment());
        dto.setDate(comment.getDateTime());

        Board board = comment.getBoard();
        dto.setBoardId(board.getId());

        User user = comment.getUser();
        dto.setId(user.getId());
        dto.setUserName(user.getName());

        return dto;
    }

    public static CommentDTO toDto(Optional<Comment> comment) {
        if(!comment.isPresent()) return null;
        return toDto(comment.get());
    }

    public Comment toEntity(CommentDTO dto) {
        Comment comment = new Comment();
        comment.setComment(dto.getComment());
        comment.setDateTime(dto.getDate());

        User user = new User();
        user.setId(dto.getUserId());
        comment.setUser(user);

        Board board = new Board();
        board.setId(dto.getBoardId());
        comment.setBoard(board);

        return comment;
    }
}
