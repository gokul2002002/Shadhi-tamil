package com.sayit.shadhi.Models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Builder
public class Astrologer {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private long astrologerId;

     @Column(unique = true)
     private String email;
     private String name;
     private String password;
     @Column(
             unique = true
     )
     private String Certrificate;
     private int YOE;
     private double price;
     private int age;

     @OneToMany
     private List<ChartRequest> chartRequests;

}
