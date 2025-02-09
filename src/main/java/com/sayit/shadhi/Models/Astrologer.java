package com.sayit.shadhi.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Astrologer {

     @Id
     @GeneratedValue(
             strategy = GenerationType.IDENTITY
     )
     private long astrologerId;

     @Column(
             unique = true
     )
     private String userName;
     private String name;

     @Column(
             unique = true
     )
     private String Certrificate;
     private int YOE;
     private double price;

     @OneToMany(mappedBy = "astrologer" , cascade = CascadeType.ALL)
     private List<ChartRequest> chartRequests;

}
