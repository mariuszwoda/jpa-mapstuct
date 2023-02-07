package com.example.jpamapstruct.service;

import cn.hutool.core.bean.BeanUtil;
import com.example.jpamapstruct.dto.ClubDto;
import com.example.jpamapstruct.entity.Club;
import com.example.jpamapstruct.mapper.ClubMapper;
import com.example.jpamapstruct.repository.ClubRepository;
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
public class ClubService {
    private final ClubRepository repository;
    private final ClubMapper clubMapper;

    public ClubService(ClubRepository repository, ClubMapper clubMapper) {
        this.repository = repository;
        this.clubMapper = clubMapper;
    }

    public ClubDto save(ClubDto clubDto) {
        Club entity = clubMapper.toEntity(clubDto);
        return clubMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public ClubDto findById(Long id) {
        return clubMapper.toDto(repository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    public Page<ClubDto> findByCondition(ClubDto clubDto, Pageable pageable) {
        Page<Club> entityPage = repository.findAll(pageable);
        List<Club> entities = entityPage.getContent();
        return new PageImpl<>(clubMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public ClubDto update(ClubDto clubDto, Long id) {
        ClubDto data = findById(id);
        Club entity = clubMapper.toEntity(clubDto);
        BeanUtil.copyProperties(data, entity);
        return save(clubMapper.toDto(entity));
    }
}