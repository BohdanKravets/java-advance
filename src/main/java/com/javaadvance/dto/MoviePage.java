package com.javaadvance.dto;

import com.javaadvance.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MoviePage {
    private List<MovieDto> movies;
    private long totalElements;
    private boolean last;
    private int currentPage;
}
