package com.example.deliciousbusan.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ShopTb {
  @Id
  int shopId;
  String shopName;
  String shopDesc;
  String address;
  String telNum;
  String parkingInfo;
  String latitude;
  String longitude;
  String regDate;
}
