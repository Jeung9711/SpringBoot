package com.example.deliciousbusan.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UserTb {
  @Id
  int userId;
  String userName;
  String email;
  String pass;
  String phoneNum;
  String regDate;
}
