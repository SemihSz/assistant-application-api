package com.crypto.request.number_of_coins;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by Semih, 25.04.2022
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Setter
public class UserAddNumberOfCoins {

    private String coinName;

    private String coinId;

    private BigDecimal numberOfCoins;

    private BigDecimal coinBuyPrice;

    private String userId;
}
