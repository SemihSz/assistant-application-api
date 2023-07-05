package com.assistant.log.entity;

import lombok.*;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class LoggerEntity {
    @GeneratedValue
    @Id
    private Long id;

    @Basic
    private String IP;

    private String application;

    private String requestBody;

    private String responseBody;

    private String requestDate;

    private String responseDate;

    private Integer status;
}
