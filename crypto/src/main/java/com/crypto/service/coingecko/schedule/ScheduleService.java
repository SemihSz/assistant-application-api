package com.crypto.service.coingecko.schedule;

import com.crypto.entity.CoinWhiteListEntity;
import com.crypto.entity.CoinsPriceEntity;
import com.crypto.model.coingecko.CoingeckoModel;
import com.crypto.model.input.GetCoinInfoListInput;
import com.crypto.repository.CoinsPriceEntityRepository;
import com.crypto.service.coingecko.getcoinslist.GetCoinInfoListService;
import com.crypto.service.coingecko.whitelist.GetWhiteListInfoService;
import com.crypto.type.Currency;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Created by Semih, 7.04.2022
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Slf4j
@Component
public class ScheduleService {

    // TODO whitelist üzerinden coin id alınacak hepsinin tek tek fiyatları alınacak sonra tablo kayıt atılacak

    private final GetWhiteListInfoService getWhiteListInfoService;

    private final GetCoinInfoListService getCoinInfoListService;

    private final CoinsPriceEntityRepository coinsPriceEntityRepository;


    public ScheduleService(GetWhiteListInfoService getWhiteListInfoService, GetCoinInfoListService getCoinInfoListService,
                           CoinsPriceEntityRepository coinsPriceEntityRepository) {
        this.getWhiteListInfoService = getWhiteListInfoService;
        this.getCoinInfoListService = getCoinInfoListService;
        this.coinsPriceEntityRepository = coinsPriceEntityRepository;
    }

    @Scheduled(cron = "0 */2 * ? * *") // Every 2 min
    //@Scheduled(cron = "0 0 * ? * *") // Every Hour
    public void scheduleGetWhiteListCoinPrice() {

        final List<CoinWhiteListEntity> whiteList = getWhiteListInfoService.get();

        log.info("Enter Schedule Service");
        if (whiteList.size() > 0) {
            log.info("Whitelist size bigger than 0");
            final GetCoinInfoListInput input = GetCoinInfoListInput.builder()
                    .numberOfCoins(2000)
                    .currency(Currency.USD)
                    .numberOfPage(10)
                    .build();

            final List<CoingeckoModel> coingeckoModels = getCoinInfoListService.apply(input);

            whiteList.forEach(t -> {

                log.info("Coingecko model is {} size", whiteList.size());
                final Optional<CoingeckoModel> filterCoin = coingeckoModels.stream().filter(s -> s.getId().equals(t.getCoinId())).findFirst();

                if (filterCoin.isPresent()) {

                    final CoingeckoModel coinInfo = filterCoin.get();

                    final CoinsPriceEntity coinsPrice = CoinsPriceEntity.builder()
                            .coinId(coinInfo.getId())
                            .coinName(coinInfo.getName())
                            .price(coinInfo.getCoingeckoPriceModel().getPrice())
                            .marketCap(coinInfo.getCoingeckoPriceModel().getMarketCap())
                            .time(LocalDateTime.now())
                            .volume(coinInfo.getCoingeckoPriceModel().getTotalVolume())
                            .build();

                    coinsPriceEntityRepository.save(coinsPrice);
                }

            });

        }

    }
}
