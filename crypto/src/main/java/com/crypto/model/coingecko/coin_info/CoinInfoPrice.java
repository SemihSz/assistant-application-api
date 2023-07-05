package com.crypto.model.coingecko.coin_info;

import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by Semih, 9.04.2022
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Builder
@Getter
public class CoinInfoPrice {

    private BigDecimal price;

    private BigDecimal marketCap;

    private BigDecimal volume;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime time;
}
