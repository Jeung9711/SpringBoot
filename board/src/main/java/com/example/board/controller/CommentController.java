package com.example.board.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.board.DTO.CommentDTO;
import com.example.board.entity.Comment;
import com.example.board.entity.User;
import com.example.board.repository.CommentRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class CommentController {
    @Autowired CommentRepository commentRepository;

    @PostMapping("/commentwrite")
    public String commentWrite(
        HttpSession session,
        @RequestParam String comment) {
        
        User sessionUser = (User) session.getAttribute("user_info");
        Long boardId = (Long) session.getAttribute("boardId");
        if( sessionUser != null && boardId != null) {
            CommentDTO dto = new CommentDTO();
            dto.setComment(comment);
            dto.setUserId(sessionUser.getId());
            dto.setUserName(sessionUser.getName());
            dto.setBoardId(boardId);
            dto.setDate(LocalDateTime.now());

            Comment commentEntity = dto.toEntity(dto);

            commentRepository.save(commentEntity);
        }

        return "redirect:/board/view?id="+boardId;
    }
    
    
}
