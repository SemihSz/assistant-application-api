package com.crypto.service.coingecko.core;

import com.crypto.Constant;
import com.crypto.model.cmc.marketchart.CoingeckoMarketChartModel;
import com.crypto.model.coingecko.coin_info.AllCoinListInfoModel;
import com.crypto.model.coingecko.core.*;
import com.crypto.request.coingecko.CoreCoingeckoCoinsMarketRequest;
import com.crypto.request.coingecko.CoreCoingeckoCoreRequest;
import com.crypto.request.coingecko.CoreCompaniesTreasuryRequest;
import com.crypto.request.coingecko.CoreMarketChartRequest;
import com.crypto.service.coingecko.converter.CoinMarketChartConverter;
import com.crypto.service.coingecko.getcoinslist.GetAllCoinListInfoService;
import com.crypto.utils.AsyncUtils;
import com.crypto.utils.CryptoUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;


/**
 * Created by Semih, 18.06.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CoingeckoCoreService implements CoreCoingeckoService {

    private final CoingeckoRestClient coingeckoRestClient;

    private final CoinMarketChartConverter coinMarketChartConverter;

    private final CompletableFutureCoinsMarketService completableFutureCoinsMarketService;

    private final CoreCoinsMarketService coreCoinsMarketService;

    private final GetAllCoinListInfoService getAllCoinListInfoService;

    @Override
    public CoreCoingeckoCoinsMarket[] coinsMarket(CoreCoingeckoCoinsMarketRequest request) {

        if (request.getNumberOfPage() > 1) {

            final List<CoreCoingeckoCoinsMarket> coingeckoCoinsMarkets = new ArrayList<>(request.getNumberOfPage());
            final List<CompletableFuture<CoreCoingeckoCoinsMarket[]>> futures = new ArrayList<>();

            for (int i = 1; i <= request.getNumberOfPage(); i++) {
                final CoreCoingeckoCoinsMarketRequest completableFutureRequest = CoreCoingeckoCoinsMarketRequest.builder()
                        .numberOfPage(i)
                        .currency(request.getCurrency())
                        .numberOfCoins(request.getNumberOfCoins())
                        .build();
                futures.add(completableFutureCoinsMarketService.apply(completableFutureRequest));
            }

            try {
                AsyncUtils.waitAllTypedFutures(futures);
            } catch (CompletionException | CancellationException e) {
                log.error(e.getMessage());
            }

            futures.forEach(t -> {
                try {
                    Collections.addAll(coingeckoCoinsMarkets, t.get());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            });
            return coingeckoCoinsMarkets.toArray(new CoreCoingeckoCoinsMarket[coingeckoCoinsMarkets.size()]);
        }
        else {

            return coreCoinsMarketService.apply(request);
        }
    }

    @Override
    public CoingeckoTrendModel trendCoins() {

        final CoreCoingeckoCoreRequest coreRequest = CoreCoingeckoCoreRequest.builder()
                .url(Constant.Coingecko.Request.TREND_COINS)
                .build();

        final CoingeckoTrendModel trendModel = (CoingeckoTrendModel) coingeckoRestClient.apply(coreRequest, CoingeckoTrendModel.class);

        return trendModel;
    }

    @Override
    public CoingeckoMarketChartModel marketChart(CoreMarketChartRequest request) {

        final String urlGeneration = CryptoUtils.requestParamUrlGeneration(Constant.Coingecko.Request.MARKET_CHART, Constant.Coingecko.Request.MARKET_CHART_ID_CONTROL, request.getId());
        final Map<String, Object> queryParams = new HashMap<>();
        queryParams.put(Constant.Coingecko.Request.QueryParam.VS_CURRENCY, request.getCurrency().getName());
        queryParams.put(Constant.Coingecko.Request.QueryParam.DAYS, request.getNumberOfDays());
        if (Objects.nonNull(request.getInterval())) {
            queryParams.put(Constant.Coingecko.Request.QueryParam.INTERVAL, request.getInterval());
        }

        final CoreCoingeckoCoreRequest coreRequest = CoreCoingeckoCoreRequest.builder()
                .url(Constant.Coingecko.Request.BASE_URL.concat(urlGeneration))
                .queryParams(queryParams)
                .build();

        final CoinMarketChartModel coinMarketChartModel = (CoinMarketChartModel) coingeckoRestClient.apply(coreRequest, CoinMarketChartModel.class);

        final CoingeckoMarketChartModel chartModel = coinMarketChartConverter.apply(coinMarketChartModel);

        return chartModel;
    }

    @Override
    public CoingeckoCategoriesModel[] categoriesList() {

        final CoreCoingeckoCoreRequest coreRequest = CoreCoingeckoCoreRequest.builder()
                .url(Constant.Coingecko.Request.CATEGORIES_LIST)
                .build();

        final CoingeckoCategoriesModel[] response = (CoingeckoCategoriesModel[]) coingeckoRestClient.apply(coreRequest, CoingeckoCategoriesModel[].class);

        return response;
    }

    @Override
    public CoreCompaniesTreasury companiesTreasury(CoreCompaniesTreasuryRequest request) {

        final List<AllCoinListInfoModel> getAllCoins = getAllCoinListInfoService.get();

        final Optional<AllCoinListInfoModel> model = getAllCoins.stream().filter(t -> t.getCoinId().equals(request.getCoinId().toLowerCase(Locale.ROOT))).findFirst();

        if (model.isPresent()) {

            final CoreCoingeckoCoreRequest coreRequest = CoreCoingeckoCoreRequest.builder()
                    .url(Constant.Coingecko.Request.COMPANIES_TREASURY.concat(model.get().getCoinId()))
                    .build();

            final CoreCompaniesTreasury response = (CoreCompaniesTreasury) coingeckoRestClient.apply(coreRequest, CoreCompaniesTreasury.class);

            return response;
        }

        return null;
    }

    // TODO /coins/{id}/history servisiyle ilgili bilgileri reddit benzeri ne kadar trend olmuş görebiliriz.

}
