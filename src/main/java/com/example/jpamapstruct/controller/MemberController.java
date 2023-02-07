package com.example.jpamapstruct.controller;

import com.example.jpamapstruct.dto.MemberDto;
import com.example.jpamapstruct.entity.Member;
import com.example.jpamapstruct.mapper.MemberMapper;
import com.example.jpamapstruct.service.MemberService;
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

@RequestMapping("/member")
@RestController
@Slf4j
//@Api("member")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/save")
    public ResponseEntity<Void> save(@RequestBody @Validated MemberDto memberDto) {
        memberService.save(memberDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberDto> findById(@PathVariable("id") Long id) {
        MemberDto member = memberService.findById(id);
        return ResponseEntity.ok(member);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        Optional.ofNullable(memberService.findById(id)).orElseThrow(() -> {
            log.error("Unable to delete non-existent data！");
            return new ResourceNotFoundException();
        });
        memberService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page-query")
    public ResponseEntity<Page<MemberDto>> pageQuery(MemberDto memberDto, @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<MemberDto> memberPage = memberService.findByCondition(memberDto, pageable);
        return ResponseEntity.ok(memberPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated MemberDto memberDto, @PathVariable("id") Long id) {
        memberService.update(memberDto, id);
        return ResponseEntity.ok().build();
    }
}