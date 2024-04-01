package com.blogapp56.repository;

import com.blogapp56.entity.Letter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LetterRepository  extends JpaRepository<Letter, Long> {

}
