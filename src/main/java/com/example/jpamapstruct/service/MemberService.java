package com.example.jpamapstruct.service;

import cn.hutool.core.bean.BeanUtil;
import com.example.jpamapstruct.dto.MemberDto;
import com.example.jpamapstruct.entity.Member;
import com.example.jpamapstruct.mapper.MemberMapper;
import com.example.jpamapstruct.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class MemberService {
    private final MemberRepository repository;
    private final MemberMapper memberMapper;

    public MemberService(MemberRepository repository, MemberMapper memberMapper) {
        this.repository = repository;
        this.memberMapper = memberMapper;
    }

    public MemberDto save(MemberDto memberDto) {
        Member entity = memberMapper.toEntity(memberDto);
        return memberMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public MemberDto findById(Long id) {
        return memberMapper.toDto(repository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    public Page<MemberDto> findByCondition(MemberDto memberDto, Pageable pageable) {
        Page<Member> entityPage = repository.findAll(pageable);
        List<Member> entities = entityPage.getContent();
        return new PageImpl<>(memberMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public MemberDto update(MemberDto memberDto, Long id) {
        MemberDto data = findById(id);
        Member entity = memberMapper.toEntity(memberDto);
        BeanUtil.copyProperties(data, entity);
        return save(memberMapper.toDto(entity));
    }
}