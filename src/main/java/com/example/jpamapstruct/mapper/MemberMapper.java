package com.example.jpamapstruct.mapper;

import com.example.jpamapstruct.dto.ClubDto;
import com.example.jpamapstruct.dto.MemberDto;
import com.example.jpamapstruct.entity.Club;
import com.example.jpamapstruct.entity.Member;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface MemberMapper extends EntityMapper<MemberDto, Member> {


//    @Mapping(target = "version")
//    Club memberDtoToClub(MemberDto memberDto);

//    @Mapping(target = "version", source = "version")
//    Club toEntity(ClubDto clubDto);

//    Club clubDtoToClub(ClubDto clubDto);
//
//    ClubDto toDto(Club club);
//
//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    Club toEntity(ClubDto clubDto, @MappingTarget Club club);

    //
//    @Mapping(target = "club", source = "")
//    @Mapping(target = "club", ignore = true)
//    @Mapping(target = "events", source = "events")
    @Mapping(target = "club.id", source = "clubId")
    Member toEntity(MemberDto memberDto);

    @Mapping(target = "clubId", source = "club.id")
//    @Mapping(target = "clubName", source = "club.name")
    MemberDto toDto(Member member);
//
//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    Member toEntity(MemberDto memberDto, @MappingTarget Member member);
}