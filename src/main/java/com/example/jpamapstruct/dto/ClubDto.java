package com.example.jpamapstruct.dto;

import lombok.*;

//@ApiModel()
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
//public class ClubDto {
public class ClubDto extends AbstractDto {
    private Long id;
    private String name;
}