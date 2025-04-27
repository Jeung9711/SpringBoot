package com.example.company.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Departments {
  @Id
  int departmentId;

  String departmentName;

  @ManyToOne
  @JoinColumn(name = "location_id")
  Locations locations;
}
