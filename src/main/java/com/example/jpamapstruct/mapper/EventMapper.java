package com.example.jpamapstruct.mapper;

import com.example.jpamapstruct.dto.EventDto;
import com.example.jpamapstruct.entity.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EventMapper extends EntityMapper<EventDto, Event> {

}