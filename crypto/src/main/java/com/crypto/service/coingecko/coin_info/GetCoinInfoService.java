package com.crypto.service.coingecko.coin_info;

import com.crypto.entity.CoinWhiteListEntity;
import com.crypto.entity.CoinsPriceEntity;
import com.crypto.model.coingecko.coin_info.CoinInfoModel;
import com.crypto.model.coingecko.coin_info.CoinInfoPrice;
import com.crypto.repository.CoinsPriceEntityRepository;
import com.crypto.service.coingecko.whitelist.GetWhiteListInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Created by Semih, 9.04.2022
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GetCoinInfoService implements Supplier<List<CoinInfoModel>> {

    private final GetWhiteListInfoService getWhiteListInfoService;

    private final CoinsPriceEntityRepository coinsPriceEntityRepository;

    @Override
    public List<CoinInfoModel> get() {

        final List<CoinWhiteListEntity> whiteList = getWhiteListInfoService.get();
        final List<CoinsPriceEntity> coinPriceList = coinsPriceEntityRepository.findAll();
        final List<CoinInfoModel> responseList = new ArrayList<>();

        if (whiteList.size() > 0 && coinPriceList.size() > 0) {

            whiteList.forEach(f -> {

                final List<CoinsPriceEntity> filterCoinList = coinPriceList.stream().filter(t -> t.getCoinId().equals(f.getCoinId())).collect(Collectors.toList());

                final CoinsPriceEntity firstValue = filterCoinList.get(0);
                final List<CoinInfoPrice> priceList = new ArrayList<>();

                filterCoinList.forEach(t -> {

                    final CoinInfoPrice price = CoinInfoPrice.builder()
                            .price(t.price)
                            .marketCap(t.marketCap)
                            .volume(t.volume)
                            .time(t.time)
                            .build();

                    priceList.add(price);
                });


               final CoinInfoModel infoModel = CoinInfoModel.builder()
                       .coinName(firstValue.getCoinName())
                       .coinId(firstValue.getCoinId())
                       .priceList(priceList)
                       .build();

                responseList.add(infoModel);
            });

           return responseList;
        }


        return null;
    }
}
