package com.crypto.model.coingecko.coin_generator;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Semih, 23.09.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Setter
@Builder
public class FieldSpecInput<Y> {

    private Class<?> annotation;

    private Class<Y> fieldType;

    private String name;
}
