package com.example.deliciousbusan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.deliciousbusan.entity.FavoriteTb;

public interface FavoriteTbRepository extends JpaRepository<FavoriteTb, Integer> {
  
}
