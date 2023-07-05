package com.earthquake.api.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Semih, 11.02.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfigEntity {

    @Id
    private String parameterName;

    private String value;

    private String secondValue;
}
