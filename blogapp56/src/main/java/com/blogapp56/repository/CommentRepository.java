package com.blogapp56.repository;

import com.blogapp56.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByLetterId(long id);
}
