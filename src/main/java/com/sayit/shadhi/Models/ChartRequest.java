package com.sayit.shadhi.Models;

import com.sayit.shadhi.Enums.AstrologyStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "chartRequest")
public class ChartRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long requestID;

    private long brideID;

    private long groomID;

    private long astrologerID;

    @Enumerated(value = EnumType.ORDINAL)
    private AstrologyStatus status;

    private String brideDocument;

    private String GroomDocument;

    private long score;
}
