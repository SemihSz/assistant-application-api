package com.crypto.service.coingecko.getcoinslist;

import com.crypto.model.coingecko.CoingeckoModel;
import com.crypto.model.input.GetCoinInfoListInput;
import com.crypto.request.coingecko.CoreCoingeckoCoinsMarketRequest;
import com.crypto.service.coingecko.CoingeckoConverter;
import com.crypto.service.coingecko.core.CoreCoingeckoService;
import com.assistant.commonapi.task.SimpleTask;
import com.crypto.type.Currency;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Semih, 29.04.2022
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GetCoinInfoListService implements SimpleTask<GetCoinInfoListInput,List<CoingeckoModel>> {

    private final CoreCoingeckoService coreCoingeckoService;

    private final CoingeckoConverter coingeckoConverter;

    @Override
    public List<CoingeckoModel> apply(GetCoinInfoListInput getCoinInfoListInput) {

        final CoreCoingeckoCoinsMarketRequest coreRequest = CoreCoingeckoCoinsMarketRequest.builder()
                .numberOfCoins(getCoinInfoListInput.getNumberOfCoins())
                .currency(Currency.USD)
                .numberOfPage(getCoinInfoListInput.getNumberOfPage())
                .build();

        return coingeckoConverter.apply(coreCoingeckoService.coinsMarket(coreRequest));
    }

}
