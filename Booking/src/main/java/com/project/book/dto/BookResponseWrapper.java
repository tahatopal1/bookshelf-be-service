package com.project.book.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookResponseWrapper {

    private String kind;
    private float totalItems;
    ArrayList<BookResponse> items = new ArrayList<>();

}
