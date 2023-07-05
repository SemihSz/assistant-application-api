package com.crypto.service.coingecko.getcoinslist;

import com.crypto.entity.CoinWhiteListEntity;
import com.crypto.exception.CryptoException;
import com.crypto.service.coingecko.whitelist.GetWhiteListInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * Created by Semih, 24.09.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CoinWhiteListControl implements Consumer<String> {

    private final GetWhiteListInfoService getWhiteListInfoService;

    @Override
    public void accept(String coinId) {

        final List<CoinWhiteListEntity> entityList = getWhiteListInfoService.get();

        final Optional<CoinWhiteListEntity> optional = entityList.stream().filter(t -> t.getCoinId().equals(coinId)).findAny();

        if (optional.isPresent()) {
            throw new CryptoException("This whitelistCoin exist");
        }

    }
}
