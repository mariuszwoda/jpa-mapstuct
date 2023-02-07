package com.example.jpamapstruct.controller;

import com.example.jpamapstruct.dto.MemberDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collections;
import java.util.List;

public class MemberBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static MemberDto getDto() {
        MemberDto dto = new MemberDto();
        dto.setId(1L);
        return dto;
    }
}