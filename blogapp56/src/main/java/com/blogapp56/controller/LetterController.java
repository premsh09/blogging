package com.blogapp56.controller;

import com.blogapp56.payload.LetterDto;
import com.blogapp56.payload.ListLetterDto;
import com.blogapp56.service.LetterService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/letter")

public class LetterController {

    private LetterService letterService;

    public LetterController(LetterService letterService) {
        this.letterService = letterService;
    }


    // http://localhost:8080/api/letter
    @PostMapping
    public ResponseEntity<?> createLetter(@Valid @RequestBody LetterDto letterDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR );
        }
        LetterDto dto = letterService.createLetter(letterDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
   // http://localhost:8080/api/letter/2
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLetter(@PathVariable long id){
        letterService.deleteLetter(id);
        return new ResponseEntity<>("Letter Deleted!", HttpStatus.OK);
    }

    //http://localhost:8080/api/letter?pageNo=0&pageSize=3&sortBy=title&sortDir=desc
    @GetMapping
    public ResponseEntity<ListLetterDto> fetchAll(
            @RequestParam(name = "pageNo",defaultValue = "0",required = false)int pageNo,
            @RequestParam(name = "pageSize",defaultValue = "3",required = false)int pageSize,
            @RequestParam(name = "sortBy",defaultValue = "id",required = false)String sortBy,
            @RequestParam(name = "sortDir",defaultValue = "asc",required = false)String sortDir
    ){
        ListLetterDto listLetterDto = letterService.fetchAll(pageNo,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(listLetterDto, HttpStatus.OK);
    }

    // http://localhost:8080/api/letter/1
    @GetMapping("/{id}")
    public ResponseEntity<LetterDto> getLetterById(@PathVariable long id){
        LetterDto dto = letterService.getLetterById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
