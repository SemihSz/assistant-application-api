package com.crypto.service.coingecko.core;

import com.crypto.Constant;
import com.crypto.model.coingecko.core.CoreCoingeckoCoinsMarket;
import com.crypto.request.coingecko.CoreCoingeckoCoinsMarketRequest;
import com.crypto.request.coingecko.CoreCoingeckoCoreRequest;
import com.assistant.commonapi.task.SimpleTask;
import com.crypto.type.Currency;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Semih, 10.04.2022
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CoreCoinsMarketService implements SimpleTask<CoreCoingeckoCoinsMarketRequest,  CoreCoingeckoCoinsMarket[]> {

    private final CoingeckoRestClient coingeckoRestClient;

    @Override
    public CoreCoingeckoCoinsMarket[] apply(CoreCoingeckoCoinsMarketRequest request) {

        final Map<String, Object> queryParams = new HashMap<>();
        queryParams.put(Constant.Coingecko.Request.QueryParam.VS_CURRENCY, Objects.nonNull(request.getCurrency().getName()) ? request.getCurrency().getName() : Currency.USD.getName());
        queryParams.put(Constant.Coingecko.Request.QueryParam.ORDER, "market_cap_desc");
        queryParams.put(Constant.Coingecko.Request.QueryParam.PER_PAGE, request.getNumberOfCoins().toString());
        queryParams.put(Constant.Coingecko.Request.QueryParam.SPARKLINE, false);
        queryParams.put(Constant.Coingecko.Request.QueryParam.PAGE, request.getNumberOfPage().toString());

        final CoreCoingeckoCoreRequest coreRequest = CoreCoingeckoCoreRequest.builder()
                .url(Constant.Coingecko.Request.COINS_MARKET)
                .queryParams(queryParams)
                .build();


        CoreCoingeckoCoinsMarket[] response = (CoreCoingeckoCoinsMarket[]) coingeckoRestClient.apply(coreRequest, CoreCoingeckoCoinsMarket[].class);

        return response;
    }
}
