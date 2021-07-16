package com.javaadvance.dto;

import com.javaadvance.entity.Apartment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApartmentPage {
    List<Apartment> apartmentList;
    int totalPages;
    long totalElements;
}
