package com.example.ala.sport;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class PageResponse <T>{
    private List<T> content;
    private int number;
    private int size;
    private long totalElement;
    private int totalPages;
    private boolean first;
    private boolean last;
}
