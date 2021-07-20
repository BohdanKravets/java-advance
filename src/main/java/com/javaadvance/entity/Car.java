package com.javaadvance.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(exclude = "user")
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String brand;
    private double engineVolume;
    private String color;

    @ManyToOne
    @JsonIgnore
    private User user;
}
