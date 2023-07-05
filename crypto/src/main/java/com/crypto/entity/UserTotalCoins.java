package com.crypto.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by Semih, 25.04.2022
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@IdClass(UserTotalCoins.PK.class)
public class UserTotalCoins {

    @Id
    private String userId;

    @Id
    private LocalDateTime currenTDate;

    public String coinId;

    public String coinName;

    private BigDecimal numberOfCoins;

    private BigDecimal coinBuyPrice;

    private BigDecimal coinCurrentPrice;

    private BigDecimal buyCurrentPriceChange;

    @EqualsAndHashCode
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PK implements Serializable {

        private LocalDateTime currenTDate;

        private String userId;
    }


}
