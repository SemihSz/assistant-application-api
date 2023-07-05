package com.crypto.request.coingecko;

import com.crypto.type.CoingeckoFlowType;
import com.crypto.type.Currency;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Semih, 16.06.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Setter
public class CoingeckoRequest {

    private CoingeckoFlowType coingeckoFlowType;

    private Integer numberOfCoins;

    private Currency currency;

    private String idOrName;

    private String interval;

    private int numberOfDays;

    private Integer numberOfPage;

}
