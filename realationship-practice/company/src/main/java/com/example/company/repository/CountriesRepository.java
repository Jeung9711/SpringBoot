package com.example.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.company.entity.Countries;

public interface CountriesRepository extends JpaRepository<Countries, String> {
  
}
