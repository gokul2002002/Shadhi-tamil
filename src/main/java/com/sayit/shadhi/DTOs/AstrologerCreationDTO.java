package com.sayit.shadhi.DTOs;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AstrologerCreationDTO {
    private String name;
    private String email;
    private int age;
    private int YOE;
    private MultipartFile certrificate;
    private double price;
    private String password;
}
