package com.blogapp56.service;

import com.blogapp56.entity.Letter;
import com.blogapp56.exception.ResourceNotFound;
import com.blogapp56.payload.LetterDto;
import com.blogapp56.payload.ListLetterDto;
import com.blogapp56.repository.LetterRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LetterServiceImpl implements LetterService {

    private LetterRepository letterRepository;

    private ModelMapper modelMapper;

    public LetterServiceImpl(LetterRepository letterRepository, ModelMapper modelMapper) {
        this.letterRepository = letterRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public LetterDto createLetter(LetterDto letterDto) {
        Letter letter = mapToEntity(letterDto);

        Letter savedLetter = letterRepository.save(letter);

        LetterDto dto = mapToDto(savedLetter);

        return dto;

    }

    @Override
    public void deleteLetter(long id) {
        letterRepository.deleteById(id);
    }

    @Override
    public ListLetterDto fetchAll(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending()
                :Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Letter> all = letterRepository.findAll(pageable);
        List<Letter> letters = all.getContent();
        List<LetterDto> letterDtos = letters.stream().map(letter -> mapToDto(letter)).collect(Collectors.toList());
        ListLetterDto listLetterDto = new ListLetterDto();
        listLetterDto.setLetterDto(letterDtos);
        listLetterDto.setTotalPage(all.getTotalPages());
        listLetterDto.setTotalElement((int) all.getTotalElements());
        listLetterDto.setPageNumber(all.getNumber());
        listLetterDto.setFirstPage(all.isFirst());
        listLetterDto.setLastPage(all.isLast());
        return listLetterDto;
    }

    @Override
    public LetterDto getLetterById(long id) {
        Letter letter = letterRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Letter not found with id :" + id));
        return mapToDto(letter);
    }

    Letter mapToEntity(LetterDto letterDto){
        Letter letter = modelMapper.map(letterDto, Letter.class);
        return letter;
    }

    LetterDto mapToDto(Letter letter){
        LetterDto dto = modelMapper.map(letter, LetterDto.class);
        return  dto;
    }
}
