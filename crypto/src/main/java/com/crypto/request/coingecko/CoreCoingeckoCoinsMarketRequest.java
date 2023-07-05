package com.crypto.request.coingecko;

import com.crypto.type.Currency;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Semih, 18.06.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Setter
@Builder

public class CoreCoingeckoCoinsMarketRequest {

    private Integer numberOfCoins;

    private Currency currency;

    private Integer numberOfPage;

}
