package com.example.jpamapstruct.dto;

import com.example.jpamapstruct.entity.Club;
import com.example.jpamapstruct.entity.Event;
import lombok.*;

import java.util.List;

//@ApiModel()
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
//public class MemberDto extends AbstractDto<Long> {
public class MemberDto {
    private Long id;
    private String name;
//    private Club club;
    private Long clubId;
//    private String clubName;
    private List<EventDto> events;
}