package com.blogapp56.payload;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class LetterDto {

    private long id;
    @NotEmpty
    @Size(min = 5,message = "Title should be atleast 5 characters")
    private String title;
    private String content;
    private String description;
}
