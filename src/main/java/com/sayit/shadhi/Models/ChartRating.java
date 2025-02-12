package com.sayit.shadhi.Models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@Table(name = "chart_rating")
public class ChartRating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ratingId;

    private Double rating;

    @OneToOne
    private Astrologer astrologer;

    @ManyToOne
    private ChartRequest chartRequest;

}
