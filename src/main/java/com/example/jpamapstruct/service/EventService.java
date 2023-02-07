package com.example.jpamapstruct.service;

import cn.hutool.core.bean.BeanUtil;
import com.example.jpamapstruct.dto.EventDto;
import com.example.jpamapstruct.entity.Event;
import com.example.jpamapstruct.mapper.EventMapper;
import com.example.jpamapstruct.repository.EventRepository;
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
public class EventService {
    private final EventRepository repository;
    private final EventMapper eventMapper;

    public EventService(EventRepository repository, EventMapper eventMapper) {
        this.repository = repository;
        this.eventMapper = eventMapper;
    }

    public EventDto save(EventDto eventDto) {
        Event entity = eventMapper.toEntity(eventDto);
        return eventMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public EventDto findById(Long id) {
        return eventMapper.toDto(repository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    public Page<EventDto> findByCondition(EventDto eventDto, Pageable pageable) {
        Page<Event> entityPage = repository.findAll(pageable);
        List<Event> entities = entityPage.getContent();
        return new PageImpl<>(eventMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public EventDto update(EventDto eventDto, Long id) {
        EventDto data = findById(id);
        Event entity = eventMapper.toEntity(eventDto);
        BeanUtil.copyProperties(data, entity);
        return save(eventMapper.toDto(entity));
    }
}