package com.crypto.service.coingecko.core;

import com.crypto.model.coingecko.core.CoreCoingeckoCoinsMarket;
import com.crypto.request.coingecko.CoreCoingeckoCoinsMarketRequest;
import com.assistant.commonapi.task.SimpleTask;
import com.crypto.utils.AsyncUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * Created by Semih, 10.04.2022
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CompletableFutureCoinsMarketService implements SimpleTask<CoreCoingeckoCoinsMarketRequest, CompletableFuture<CoreCoingeckoCoinsMarket[]>> {

    private final CoreCoinsMarketService coreCoinsMarketService;

    @Override
    public CompletableFuture<CoreCoingeckoCoinsMarket[]> apply(CoreCoingeckoCoinsMarketRequest request) {

        return AsyncUtils.convertToFuture(coreCoinsMarketService.apply(request));
    }
}
