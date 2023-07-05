package com.crypto.service.coingecko;

import com.crypto.model.coingecko.CoingeckoApiResponse;
import com.crypto.request.coingecko.CoingeckoRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Created by Semih, 18.06.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CoingeckoService implements GeneralCoingeckoService {

    private final CoingeckoFlowService coingeckoFlowService;


    @Override
    public CoingeckoApiResponse<?> coingeckoGeneralMarket(CoingeckoRequest request) {

        if (Objects.nonNull(request.getCoingeckoFlowType())) {

            switch (request.getCoingeckoFlowType()) {

                case COINS_MARKET:
                    return coingeckoFlowService.coinsMarket(request);

                case TRENDS_COINS:
                    return coingeckoFlowService.trendCoins();

                case MARKET_CHART:
                    return coingeckoFlowService.marketChart(request);

                case COIN_PRICE_INFO:
                    return coingeckoFlowService.whiteListCoinInfo();

                case CATEGORIES_LIST:
                    return coingeckoFlowService.getCategoriesList();

                case ALL_COIN_LIST_NAME:
                    return coingeckoFlowService.allCoinList();

                case SAVE_ALL_COINS:
                    return coingeckoFlowService.saveAllCoins();

                case COMPANIES_TREASURY:
                    return coingeckoFlowService.companiesTreasury(request);
                // TODO CoinList Return Case

                default:
                    log.error("error flow type");
                    break;
            }
        }
        return null;
    }
}
