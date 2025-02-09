package com.sayit.shadhi.Models;

import com.sayit.shadhi.Enums.AstrologyStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "chartRequest")
@NoArgsConstructor
public class ChartRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long requestID;

    @ManyToOne
    @JoinColumn(name = "astrologer" , referencedColumnName = "astrologerId")
    private Astrologer astrologer;

    @Enumerated(value = EnumType.ORDINAL)
    private AstrologyStatus status;

    private List<User> listOfUsers;

    private Double score;
}
