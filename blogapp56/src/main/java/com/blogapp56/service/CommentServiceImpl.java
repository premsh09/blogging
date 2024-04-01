package com.blogapp56.service;

import com.blogapp56.entity.Comment;
import com.blogapp56.entity.Letter;
import com.blogapp56.payload.CommentDto;
import com.blogapp56.payload.LetterDto;
import com.blogapp56.payload.LetterWithCommentDto;
import com.blogapp56.repository.CommentRepository;
import com.blogapp56.repository.LetterRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService{

    private CommentRepository commentRepository;
    private ModelMapper modelMapper;
    private LetterRepository letterRepository;


    @Override
    public CommentDto createComment(CommentDto commentDto, long letterId) {
        Optional<Letter> byId = letterRepository.findById(letterId);
        Letter letter = byId.get();

        Comment comment = mapToEntity(commentDto);
        comment.setLetter(letter);
        Comment savedComment = commentRepository.save(comment);
        CommentDto dto = mapToDto(savedComment);
        return dto;
    }

    @Override
    public LetterWithCommentDto getAllCommentsByLetterId(long id) {
        Letter letter = letterRepository.findById(id).get();
        LetterDto dto = new LetterDto();
        dto.setId(letter.getId());
        dto.setTitle(letter.getTitle());
        dto.setDescription(letter.getDescription());
        dto.setContent(letter.getContent());

        List<Comment> comments = commentRepository.findByLetterId(id);
        List<CommentDto> dtos = comments.stream().map(c -> mapToDto(c)).collect(Collectors.toList());

        LetterWithCommentDto letterWithCommentDto = new LetterWithCommentDto();
        letterWithCommentDto.setLetter(dto);
        letterWithCommentDto.setCommentDto(dtos);
        return letterWithCommentDto;
    }

    Comment mapToEntity(CommentDto dto){
        return modelMapper.map(dto, Comment.class);
    }

    CommentDto mapToDto(Comment comment){
        return modelMapper.map(comment, CommentDto.class);
    }
}
