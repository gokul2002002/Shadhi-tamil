package com.sayit.shadhi.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "User")
@SuppressWarnings("all")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private long userId;

    @Column(nullable = false)
    private String password;

    @Column(
            nullable = false,
            unique = true
    )
    private String email;

    private String name;

    private String age;

    @Column(
            updatable = false
    )
    private String gender;

    private String dateOfBirth;

    private String community;

    private String religion;

    private String subCaste;

    private String educationalQualification;

    private String salaryPerAnnum;

    private String authority;

    private String profilePhoto;

    @ElementCollection
    private List<String> galary;

    @OneToOne
    @JoinColumn(name = "chart_id")
    private Chart chart;

    @ElementCollection
    private List<User> pairs;


    @PrePersist
    public void doPrepresist(){
        if (community == null){
            community = "NOT_PREFERED";
        }
        if (salaryPerAnnum == null){
            salaryPerAnnum = "NOT_PREFERED";
        }
        if(subCaste == null){
            subCaste = "NOT_PREFERED";
        }
        if (religion == null){
            religion = "NOT_PREFERED";
        }
    }
}
