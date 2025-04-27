package com.example.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.company.entity.Locations;

public interface LocationsRepository extends JpaRepository<Locations, Integer> {
  
}
