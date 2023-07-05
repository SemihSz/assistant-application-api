package com.crypto.service.coingecko.coin_generator;

import com.crypto.model.coingecko.CoingeckoApiResponse;
import com.crypto.model.input.CoinsEntityInput;
import com.crypto.request.coingecko.CoinGeneratorRequest;

/**
 * Created by Semih, 23.09.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
public abstract class CoinGeneratorService {

    public abstract CoingeckoApiResponse<?> coingeckoCoinGenerator(CoinGeneratorRequest request);

    abstract void saveCoins(CoinsEntityInput input);

    abstract void controlRepository(String coinName);
}
