package com.crypto.service.coingecko.core;

import com.crypto.model.cmc.marketchart.CoingeckoMarketChartModel;
import com.crypto.model.coingecko.core.CoingeckoCategoriesModel;
import com.crypto.model.coingecko.core.CoingeckoTrendModel;
import com.crypto.model.coingecko.core.CoreCoingeckoCoinsMarket;
import com.crypto.model.coingecko.core.CoreCompaniesTreasury;
import com.crypto.request.coingecko.CoreCoingeckoCoinsMarketRequest;
import com.crypto.request.coingecko.CoreCompaniesTreasuryRequest;
import com.crypto.request.coingecko.CoreMarketChartRequest;

/**
 * Created by Semih, 18.06.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
public interface CoreCoingeckoService {

    CoreCoingeckoCoinsMarket[] coinsMarket(CoreCoingeckoCoinsMarketRequest coinsMarketRequest);

    CoingeckoTrendModel trendCoins();

    CoingeckoMarketChartModel marketChart(CoreMarketChartRequest request);

    CoingeckoCategoriesModel[] categoriesList();

    CoreCompaniesTreasury companiesTreasury(CoreCompaniesTreasuryRequest request);
}
