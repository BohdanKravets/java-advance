package com.javaadvance.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
//@Table(name = "MOVIE_TABLE")
public class Movie {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false,length = 100)
    @NotBlank
    private String title;
    @Positive
    private int duration;

    @ManyToOne
    @JsonIgnore
    private Director director;

}
