package com.example.jpamapstruct.controller;

import com.example.jpamapstruct.dto.ClubDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collections;
import java.util.List;

public class ClubBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static ClubDto getDto() {
        ClubDto dto = new ClubDto();
        dto.setId(1L);
        dto.setName("clubName");
        return dto;
    }
}