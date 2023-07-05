package com.crypto.model.coingecko.coin_generator;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by Semih, 24.09.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Builder
public class GenericSaveRepositoryInput<Z> {

    private Z entity;

    private String name;

    public LocalDateTime time;

    public BigDecimal price;

    public BigDecimal volume;

    public BigDecimal markerCap;
}
