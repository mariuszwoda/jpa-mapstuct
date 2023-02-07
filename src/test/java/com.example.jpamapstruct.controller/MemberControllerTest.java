package com.example.jpamapstruct.controller;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.example.jpamapstruct.controller.CustomUtils;
import com.example.jpamapstruct.controller.MemberController;
import com.example.jpamapstruct.dto.MemberDto;
import com.example.jpamapstruct.entity.Member;
import com.example.jpamapstruct.mapper.EntityMapper;
import com.example.jpamapstruct.mapper.MemberMapper;
import com.example.jpamapstruct.service.MemberService;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;

@Transactional
public class MemberControllerTest {
    private static final String ENDPOINT_URL = "/api/member";
    @InjectMocks
    private MemberController memberController;
    @Mock
    private MemberService memberService;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(memberController)
                //.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                //.addFilter(CustomFilter::doFilter)
                .build();
    }

    @Test
    public void findAllByPage() throws Exception {
        Page<MemberDto> page = new PageImpl<>(Collections.singletonList(MemberBuilder.getDto()));

        Mockito.when(memberService.findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(page);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.content", Matchers.hasSize(1)));

        Mockito.verify(memberService, Mockito.times(1)).findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any());
        Mockito.verifyNoMoreInteractions(memberService);

    }

    @Test
    public void getById() throws Exception {
        Mockito.when(memberService.findById(ArgumentMatchers.anyLong())).thenReturn(MemberBuilder.getDto());

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
        Mockito.verify(memberService, Mockito.times(1)).findById(1L);
        Mockito.verifyNoMoreInteractions(memberService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(memberService.save(ArgumentMatchers.any(MemberDto.class))).thenReturn(MemberBuilder.getDto());

        mockMvc.perform(
                        MockMvcRequestBuilders.post(ENDPOINT_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(MemberBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(memberService, Mockito.times(1)).save(ArgumentMatchers.any(MemberDto.class));
        Mockito.verifyNoMoreInteractions(memberService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(memberService.update(ArgumentMatchers.any(), ArgumentMatchers.anyLong())).thenReturn(MemberBuilder.getDto());

        mockMvc.perform(
                        MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(MemberBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(memberService, Mockito.times(1)).update(ArgumentMatchers.any(MemberDto.class), ArgumentMatchers.anyLong());
        Mockito.verifyNoMoreInteractions(memberService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(memberService).deleteById(ArgumentMatchers.anyLong());
        mockMvc.perform(
                MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(CustomUtils.asJsonString(MemberBuilder.getIds()))).andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(memberService, Mockito.times(1)).deleteById(Mockito.anyLong());
        Mockito.verifyNoMoreInteractions(memberService);
    }
}