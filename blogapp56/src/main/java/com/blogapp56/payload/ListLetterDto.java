package com.blogapp56.payload;

import lombok.Data;

import java.util.List;

@Data
public class ListLetterDto {
    private List<LetterDto> letterDto;
    private int totalPage;
    private int totalElement;
    private int pageNumber;
    private boolean firstPage;
    private boolean lastPage;
}
