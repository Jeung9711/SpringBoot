package com.example.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.board.entity.Comment;


public interface CommentRepository extends JpaRepository<Comment, Long>{
    Page<Comment> findByBoardId(Long BoardId, Pageable pageable);
}