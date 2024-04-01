package com.blogapp56.controller;

import com.blogapp56.payload.CommentDto;
import com.blogapp56.payload.LetterWithCommentDto;
import com.blogapp56.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/comments")
@AllArgsConstructor
public class CommentController {

    private CommentService commentService;

    // http://localhost:8080/api/comments/1
    @PostMapping("/{letterId}")
    public ResponseEntity<CommentDto> createComment(
            @RequestBody CommentDto commentDto,
            @PathVariable long letterId){

        CommentDto dto = commentService.createComment(commentDto, letterId);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/{letterId}")
    public ResponseEntity<LetterWithCommentDto> getAllCommentsByLetterId(@PathVariable long letterId){
        LetterWithCommentDto allCommentsByLetterId = commentService.getAllCommentsByLetterId(letterId);
        return new ResponseEntity<>(allCommentsByLetterId, HttpStatus.OK);
    }
}
