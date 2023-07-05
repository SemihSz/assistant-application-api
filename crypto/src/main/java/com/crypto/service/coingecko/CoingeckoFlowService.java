package com.crypto.service.coingecko;

import com.crypto.model.coingecko.CoingeckoApiResponse;
import com.crypto.request.coingecko.CoingeckoRequest;
import com.crypto.request.coingecko.CoreCoingeckoCoinsMarketRequest;
import com.crypto.request.coingecko.CoreCompaniesTreasuryRequest;
import com.crypto.request.coingecko.CoreMarketChartRequest;
import com.crypto.service.coingecko.coin_info.GetCoinInfoService;
import com.crypto.service.coingecko.core.CoreCoingeckoService;
import com.crypto.service.coingecko.getcoinslist.GetAllCoinListInfoService;
import com.crypto.service.coingecko.getcoinslist.SaveAllCoinsListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created by Semih, 18.06.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class CoingeckoFlowService {

    private final CoreCoingeckoService coreCoingeckoService;

    private final CoingeckoConverter coingeckoConverter;

    private final GetCoinInfoService getCoinInfoService;

    private final GetAllCoinListInfoService getAllCoinListInfoService;

    private final SaveAllCoinsListService saveAllCoinsListService;

    public CoingeckoApiResponse<?> coinsMarket(CoingeckoRequest coingeckoRequest) {
        return CoingeckoApiResponse.builder()
                .data(coingeckoConverter.apply(coreCoingeckoService.coinsMarket(CoreCoingeckoCoinsMarketRequest.builder()
                        .numberOfCoins(coingeckoRequest.getNumberOfCoins()).currency(coingeckoRequest.getCurrency()).numberOfPage(coingeckoRequest.getNumberOfPage()).build()))).build();
    }

    public CoingeckoApiResponse<?> trendCoins() {
        return CoingeckoApiResponse.builder().data(coreCoingeckoService.trendCoins()).build();
    }

    public CoingeckoApiResponse<?> marketChart(CoingeckoRequest coingeckoRequest) {
        final CoreMarketChartRequest coreMarketChartRequest = CoreMarketChartRequest.builder()
                .currency(coingeckoRequest.getCurrency())
                .id(coingeckoRequest.getIdOrName())
                .interval(coingeckoRequest.getInterval())
                .numberOfDays(coingeckoRequest.getNumberOfDays()).build();

        return  CoingeckoApiResponse.builder().data(coreCoingeckoService.marketChart(coreMarketChartRequest)).build();
    }

    public CoingeckoApiResponse<?> whiteListCoinInfo() {

        return CoingeckoApiResponse.builder().data(getCoinInfoService.get()).build();
    }

    public CoingeckoApiResponse<?> getCategoriesList() {

        return CoingeckoApiResponse.builder().data(coreCoingeckoService.categoriesList()).build();
    }

    public CoingeckoApiResponse<?> allCoinList() {

        return CoingeckoApiResponse.builder().data(getAllCoinListInfoService.get()).build();
    }

    public CoingeckoApiResponse<?> saveAllCoins() {

        return CoingeckoApiResponse.builder().data(saveAllCoinsListService.saveOperation()).build();
    }

    public CoingeckoApiResponse<?> companiesTreasury(CoingeckoRequest request) {
        final CoreCompaniesTreasuryRequest coreCompaniesTreasuryRequest = CoreCompaniesTreasuryRequest.builder().coinId(request.getIdOrName()).build();
        return CoingeckoApiResponse.builder().data(coreCoingeckoService.companiesTreasury(coreCompaniesTreasuryRequest)).build();

    }
}
