package com.crypto.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by Semih, 7.04.2022
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@IdClass(CoinsPriceEntity.PK.class)
public class CoinsPriceEntity {

    @Id
    public String coinId;

    @Id
    public String coinName;

    @Id
    public LocalDateTime time;

    public BigDecimal price;

    public BigDecimal volume;

    public BigDecimal marketCap;

    @EqualsAndHashCode
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PK implements Serializable {

        public String coinId;

        public String coinName;

        public LocalDateTime time;
    }
}
