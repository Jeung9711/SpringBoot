package com.example.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.board.entity.FileAtch;

@Repository
public interface FileAtchRepository extends JpaRepository<FileAtch, Long>{
    List<FileAtch> findByBoardId(Long id);
}
