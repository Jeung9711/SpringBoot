package com.example.company.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Locations {
  @Id
  int locationId;

  String streetAddress;
  String postalCode;
  String city;
  String stateProvince;
 
  @ManyToOne
  @JoinColumn(name = "country_id")
  Countries countries;
}
