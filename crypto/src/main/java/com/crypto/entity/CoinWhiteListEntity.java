package com.crypto.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Semih, 24.09.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CoinWhiteListEntity {

    @Id
    private String coinId;

    private Boolean isActive;

}
