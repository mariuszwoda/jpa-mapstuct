package com.example.jpamapstruct.dto;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Setter
//@MappedSuperclass
public abstract class AbstractDto {
//public class AbstractDto<E> {
    private Integer version;
    private LocalDateTime createAt;
    private LocalDateTime lastModifiedAt;
}