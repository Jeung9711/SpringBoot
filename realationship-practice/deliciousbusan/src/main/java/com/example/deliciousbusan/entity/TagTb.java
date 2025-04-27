package com.example.deliciousbusan.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class TagTb {
  @Id
  int tagId;
  String tagName;
  int hitCnt;
}
