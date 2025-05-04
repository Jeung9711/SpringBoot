package com.example.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.board.entity.Like;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long>{
    Long countByBoardId(Long boardId);
    Boolean existsByUserIdAndBoardId(Long userId, Long boardId);
    void deleteByUserIdAndBoardId(Long userId, Long boardId);
}
