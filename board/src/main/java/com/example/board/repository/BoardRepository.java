package com.example.board.repository;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.board.entity.Board;


public interface BoardRepository extends JpaRepository<Board, Long> {
  Page<Board> findAll(Pageable pageable);
  Page<Board> findByTitleContainingOrContentContaining(
    String title, String content, Pageable Pageable);
  Optional<Board> findById(Long id);
}