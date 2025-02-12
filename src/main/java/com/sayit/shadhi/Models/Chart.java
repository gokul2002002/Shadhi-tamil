package com.sayit.shadhi.Models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Chart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String Star;

    private String sign;

    private String chart;

    @OneToOne(mappedBy = "chart")
    private User user;
}
