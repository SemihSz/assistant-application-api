package com.crypto.model.input;

import com.crypto.type.Currency;
import lombok.Builder;
import lombok.Getter;

/**
 * Created by Semih, 29.04.2022
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Builder
@Getter
public class GetCoinInfoListInput {

    private Integer numberOfCoins;

    private Currency currency;

    private Integer numberOfPage;
}
