package com.example.deliciousbusan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.deliciousbusan.entity.UserTb;

public interface UserTbRepository extends JpaRepository<UserTb, Integer> {
  
}
