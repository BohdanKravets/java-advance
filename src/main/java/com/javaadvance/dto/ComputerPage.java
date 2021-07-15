package com.javaadvance.dto;

import com.javaadvance.entity.Computer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ComputerPage {
    private List<Computer> content;
    private int totalPages;
    private long totalElements;
}
