package com.crypto.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

/**
 * Created by Semih, 10.04.2022
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AllCoinsInfoEntity {

    @Id
    public String coinId;

    public String coinName;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    public LocalDate createdDate;
}

