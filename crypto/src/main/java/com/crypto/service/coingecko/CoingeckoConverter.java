package com.crypto.service.coingecko;

import com.crypto.model.SupplyModel;
import com.crypto.model.coingecko.CoingeckoModel;
import com.crypto.model.coingecko.CoingeckoPriceModel;
import com.crypto.model.coingecko.core.CoreCoingeckoCoinsMarket;
import com.assistant.commonapi.task.SimpleTask;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Semih, 9.09.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class CoingeckoConverter implements SimpleTask<CoreCoingeckoCoinsMarket[], List<CoingeckoModel>> {

    @Override
    public List<CoingeckoModel> apply(CoreCoingeckoCoinsMarket[] coreCoingeckoCoinsMarket) {

        final List<CoreCoingeckoCoinsMarket> coreCoingeckoCoinsMarketList = Arrays.asList(coreCoingeckoCoinsMarket.clone());
        final List<CoingeckoModel> coingeckoModels = new ArrayList<>();

        coreCoingeckoCoinsMarketList.forEach(t -> {

            final CoingeckoModel model = CoingeckoModel.builder()
                    .marketCapRank(t.getMarketCapRank())
                    .id(t.getId())
                    .name(t.getName())
                    .coingeckoPriceModel(convertPriceModel(t))
                    .supplyModel(convertSupplyModel(t))
                    .lastUpdated(t.getLastUpdated())
                    .build();

            coingeckoModels.add(model);
        });

        return coingeckoModels;
    }

    private CoingeckoPriceModel convertPriceModel(CoreCoingeckoCoinsMarket model) {

        return CoingeckoPriceModel.builder()
                .marketCap(model.getMarketCap())
                .price(model.getCurrent_price())
                .priceLow24h(model.getLow24h())
                .priceHigh24h(model.getHigh24h())
                .priceChangePercentage24h(model.getPriceChangePercentage24h())
                .marketCapChange24h(model.getMarketCapChange24h())
                .ath(model.getAth())
                .totalVolume(model.getTotalVolume())
                .build();

    }

    private SupplyModel convertSupplyModel(CoreCoingeckoCoinsMarket supplyModel) {

        return SupplyModel.builder()
                .maxSupply(supplyModel.getMaxSupply())
                .totalSupply(supplyModel.getTotalSupply())
                .circulatingSupply(supplyModel.getCirculatingSupply())
                .build();

    }
}
