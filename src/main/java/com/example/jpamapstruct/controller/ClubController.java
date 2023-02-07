package com.example.jpamapstruct.controller;

import com.example.jpamapstruct.dto.ClubDto;
import com.example.jpamapstruct.entity.Club;
import com.example.jpamapstruct.mapper.ClubMapper;
import com.example.jpamapstruct.service.ClubService;
//import com.sun.tools.javac.util.DefinedBy.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/api/club")
@RestController
@Slf4j
//@Api("club")
public class ClubController {
    private final ClubService clubService;

    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @PostMapping("/save")
    public ResponseEntity<Void> save(@RequestBody @Validated ClubDto clubDto) {
        clubService.save(clubDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClubDto> findById(@PathVariable("id") Long id) {
        ClubDto club = clubService.findById(id);
        return ResponseEntity.ok(club);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        Optional.ofNullable(clubService.findById(id)).orElseThrow(() -> {
            log.error("Unable to delete non-existent data！");
            return new ResourceNotFoundException();
        });
        clubService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page-query")
    public ResponseEntity<Page<ClubDto>> pageQuery(ClubDto clubDto, @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<ClubDto> clubPage = clubService.findByCondition(clubDto, pageable);
        return ResponseEntity.ok(clubPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated ClubDto clubDto, @PathVariable("id") Long id) {
        clubService.update(clubDto, id);
        return ResponseEntity.ok().build();
    }
}