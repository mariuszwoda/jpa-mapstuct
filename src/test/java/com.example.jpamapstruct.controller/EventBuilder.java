package com.example.jpamapstruct.controller;

import com.example.jpamapstruct.dto.EventDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collections;
import java.util.List;

public class EventBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static EventDto getDto() {
        EventDto dto = new EventDto();
        dto.setId(1L);
        return dto;
    }
}