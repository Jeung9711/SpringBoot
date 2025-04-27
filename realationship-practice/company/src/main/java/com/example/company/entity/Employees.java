package com.example.company.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Employees {
  @Id
  int employeeId;
  
  String firstName;
  String lastName;
  String email;
  String phoneNumber;
  LocalDateTime hireDate;
  Integer salary;
  Integer commissionPct;
  
  @ManyToOne
  @JoinColumn(name = "department_id")
  Departments departments;
}
