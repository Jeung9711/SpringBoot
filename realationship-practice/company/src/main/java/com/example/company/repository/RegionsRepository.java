package com.example.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.company.entity.Regions;

public interface RegionsRepository extends JpaRepository<Regions, Integer> {
  
}
