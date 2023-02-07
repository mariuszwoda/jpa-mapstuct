package com.example.jpamapstruct.entity;

import com.example.jpamapstruct.dto.AbstractDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@Entity
@ToString
@EntityListeners({AuditingEntityListener.class})
//public class Club {
public class Club extends AbstractEntity {
    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    String name;
//    String address;

}
