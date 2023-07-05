package com.crypto.type;

import lombok.Getter;

/**
 * Created by Semih, 16.09.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */

@Getter
public enum Currency {

    USD("usd"),
    TL("try");

    private final String name;

    Currency(String currency) {
        this.name = currency;
    }
}
