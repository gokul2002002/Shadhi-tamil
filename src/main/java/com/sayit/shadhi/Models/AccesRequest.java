package com.sayit.shadhi.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "request")
@Getter
@Setter
@AllArgsConstructor
public class AccesRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long accesID;

    @ManyToOne
    @JoinColumn(name = "from_user")
    private User fromUser;

    @ManyToOne
    @JoinColumn(name = "to_user")
    private User toUser;

}
