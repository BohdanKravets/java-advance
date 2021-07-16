package com.javaadvance.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @JsonFormat(pattern = "dd-MM-yyy")
    private LocalDate birthDate;

    @OneToMany(targetEntity = Movie.class)
    @JoinColumn(name = "director_id")
    @JsonIgnore
    private List<Movie> movies;
}
