package com.blogapp56.payload;

import lombok.Data;

import java.util.List;

@Data
public class LetterWithCommentDto {

    private LetterDto letter;
    private List<CommentDto> commentDto;
}
