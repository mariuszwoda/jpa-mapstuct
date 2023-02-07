package com.example.jpamapstruct.mapper;

import com.example.jpamapstruct.dto.ClubDto;
import com.example.jpamapstruct.entity.Club;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClubMapper extends EntityMapper<ClubDto, Club> {

//    @Mapping(target = "version", ignore = true)
//    @Mapping(target = "createAt", ignore = true)
//    @Mapping(target = "lastModifiedAt", ignore = true)
//    Club toEntity(ClubDto clubDto);
}