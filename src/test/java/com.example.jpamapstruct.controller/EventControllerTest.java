package com.example.jpamapstruct.controller;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.example.jpamapstruct.controller.CustomUtils;
import com.example.jpamapstruct.dto.EventDto;
import com.example.jpamapstruct.entity.Event;
import com.example.jpamapstruct.mapper.EntityMapper;
import com.example.jpamapstruct.mapper.EventMapper;
import com.example.jpamapstruct.service.EventService;
//import com.sun.tools.jdi.TargetVM;
//import com.sun.tools.jdi.TargetVM.EventController;
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
public class EventControllerTest {
    private static final String ENDPOINT_URL = "/api/event";
    @InjectMocks
    private EventController eventController;
    @Mock
    private EventService eventService;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(eventController)
                //.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                //.addFilter(CustomFilter::doFilter)
                .build();
    }

    @Test
    public void findAllByPage() throws Exception {
        Page<EventDto> page = new PageImpl<>(Collections.singletonList(EventBuilder.getDto()));

        Mockito.when(eventService.findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(page);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.content", Matchers.hasSize(1)));

        Mockito.verify(eventService, Mockito.times(1)).findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any());
        Mockito.verifyNoMoreInteractions(eventService);

    }

    @Test
    public void getById() throws Exception {
        Mockito.when(eventService.findById(ArgumentMatchers.anyLong())).thenReturn(EventBuilder.getDto());

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
        Mockito.verify(eventService, Mockito.times(1)).findById(1L);
        Mockito.verifyNoMoreInteractions(eventService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(eventService.save(ArgumentMatchers.any(EventDto.class))).thenReturn(EventBuilder.getDto());

        mockMvc.perform(
                        MockMvcRequestBuilders.post(ENDPOINT_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(EventBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(eventService, Mockito.times(1)).save(ArgumentMatchers.any(EventDto.class));
        Mockito.verifyNoMoreInteractions(eventService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(eventService.update(ArgumentMatchers.any(), ArgumentMatchers.anyLong())).thenReturn(EventBuilder.getDto());

        mockMvc.perform(
                        MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(EventBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(eventService, Mockito.times(1)).update(ArgumentMatchers.any(EventDto.class), ArgumentMatchers.anyLong());
        Mockito.verifyNoMoreInteractions(eventService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(eventService).deleteById(ArgumentMatchers.anyLong());
        mockMvc.perform(
                MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(CustomUtils.asJsonString(EventBuilder.getIds()))).andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(eventService, Mockito.times(1)).deleteById(Mockito.anyLong());
        Mockito.verifyNoMoreInteractions(eventService);
    }
}