package com.crypto.service.coingecko.getcoinslist;

import com.crypto.entity.AllCoinsInfoEntity;
import com.crypto.model.coingecko.CoingeckoModel;
import com.crypto.repository.AllCoinsInfoEntityRepository;
import com.crypto.request.coingecko.CoreCoingeckoCoinsMarketRequest;
import com.crypto.service.coingecko.CoingeckoConverter;
import com.crypto.service.coingecko.core.CoreCoingeckoService;
import com.crypto.type.Currency;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Semih, 10.04.2022
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SaveAllCoinsListService {

    private final AllCoinsInfoEntityRepository allCoinsInfoEntityRepository;

    private final CoreCoingeckoService coreCoingeckoService;

    private final CoingeckoConverter coingeckoConverter;

    public Boolean saveOperation() {

        final CoreCoingeckoCoinsMarketRequest coreRequest = CoreCoingeckoCoinsMarketRequest.builder()
                .numberOfCoins(250)
                .currency(Currency.USD)
                .numberOfPage(10)
                .build();

        final List<CoingeckoModel> coingeckoModels = coingeckoConverter.apply(coreCoingeckoService.coinsMarket(coreRequest));

        coingeckoModels.forEach(t -> {

            final AllCoinsInfoEntity entity = AllCoinsInfoEntity.builder()
                    .coinId(t.getId())
                    .coinName(t.getName())
                    .createdDate(LocalDate.now())
                    .build();

            allCoinsInfoEntityRepository.save(entity);

        });

        return Boolean.TRUE;
    }
}
