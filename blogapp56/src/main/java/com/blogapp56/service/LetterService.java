package com.blogapp56.service;

import com.blogapp56.payload.LetterDto;
import com.blogapp56.payload.ListLetterDto;

public interface LetterService {
    LetterDto createLetter(LetterDto letterDto);

    void deleteLetter(long id);

    ListLetterDto fetchAll(int pageNo, int pageSize, String sortBy, String sortDir);

    LetterDto getLetterById(long id);
}
