package com.example.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.company.entity.Departments;

public interface DepartmentsRepository extends JpaRepository<Departments, Integer> {
  
}
