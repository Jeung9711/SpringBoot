package com.example.deliciousbusan.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class FavoriteTb {
  @Id
  int id;

  

  String regDate;
}
