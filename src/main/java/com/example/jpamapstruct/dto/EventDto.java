package com.example.jpamapstruct.dto;

import com.example.jpamapstruct.entity.Member;
import com.example.jpamapstruct.enums.EventType;
import lombok.*;

//@ApiModel()
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
//public class EventDto extends AbstractDto<Long> {
public class EventDto {
    private Long id;
    private String name;
    private EventType eventType;
}