package com.example.jpamapstruct.controller;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.example.jpamapstruct.controller.ClubController;
import com.example.jpamapstruct.controller.CustomUtils;
import com.example.jpamapstruct.dto.ClubDto;
import com.example.jpamapstruct.entity.Club;
import com.example.jpamapstruct.mapper.ClubMapper;
import com.example.jpamapstruct.mapper.EntityMapper;
import com.example.jpamapstruct.service.ClubService;
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
public class ClubControllerTest {
    private static final String ENDPOINT_URL = "/api/club";
    @InjectMocks
    private ClubController clubController;
    @Mock
    private ClubService clubService;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(clubController)
                //.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                //.addFilter(CustomFilter::doFilter)
                .build();
    }

    @Test
    public void findAllByPage() throws Exception {
        Page<ClubDto> page = new PageImpl<>(Collections.singletonList(ClubBuilder.getDto()));

        Mockito.when(clubService.findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(page);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.content", Matchers.hasSize(1)));

        Mockito.verify(clubService, Mockito.times(1)).findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any());
        Mockito.verifyNoMoreInteractions(clubService);

    }

    @Test
    public void getById() throws Exception {
        Mockito.when(clubService.findById(ArgumentMatchers.anyLong())).thenReturn(ClubBuilder.getDto());

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
        Mockito.verify(clubService, Mockito.times(1)).findById(1L);
        Mockito.verifyNoMoreInteractions(clubService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(clubService.save(ArgumentMatchers.any(ClubDto.class))).thenReturn(ClubBuilder.getDto());

        mockMvc.perform(
                        MockMvcRequestBuilders.post(ENDPOINT_URL + "/save")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(ClubBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(clubService, Mockito.times(1)).save(ArgumentMatchers.any(ClubDto.class));
        Mockito.verifyNoMoreInteractions(clubService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(clubService.update(ArgumentMatchers.any(), ArgumentMatchers.anyLong())).thenReturn(ClubBuilder.getDto());

        mockMvc.perform(
                        MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(ClubBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(clubService, Mockito.times(1)).update(ArgumentMatchers.any(ClubDto.class), ArgumentMatchers.anyLong());
        Mockito.verifyNoMoreInteractions(clubService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(clubService).deleteById(ArgumentMatchers.anyLong());
        mockMvc.perform(
                MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(CustomUtils.asJsonString(ClubBuilder.getIds()))).andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(clubService, Mockito.times(1)).deleteById(Mockito.anyLong());
        Mockito.verifyNoMoreInteractions(clubService);
    }
}