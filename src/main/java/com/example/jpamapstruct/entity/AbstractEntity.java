package com.example.jpamapstruct.entity;

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
@MappedSuperclass
public abstract class AbstractEntity {
//public class AbstractDto<E> {

    @Version
    private Integer version;
    @CreatedDate
    private LocalDateTime createAt;

    @LastModifiedDate
    private LocalDateTime lastModifiedAt;

//    @CreatedBy
//    private String createdBy;
//
//    @LastModifiedBy
//    private String lastModifiedBy;

}