package com.blogapp56.service;

import com.blogapp56.payload.CommentDto;
import com.blogapp56.payload.LetterWithCommentDto;

import java.util.List;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto, long letterId);

    LetterWithCommentDto getAllCommentsByLetterId(long id);
}
