package com.crypto.service.coingecko;

import com.crypto.model.coingecko.CoingeckoApiResponse;
import com.crypto.request.coingecko.CoingeckoRequest;

/**
 * Created by Semih, 18.06.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
public interface GeneralCoingeckoService {

    CoingeckoApiResponse<?> coingeckoGeneralMarket(CoingeckoRequest request);

}
