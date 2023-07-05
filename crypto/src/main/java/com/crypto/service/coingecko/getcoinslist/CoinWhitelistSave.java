package com.crypto.service.coingecko.getcoinslist;

import com.crypto.entity.CoinWhiteListEntity;
import com.crypto.model.CoinWhitelistRequest;
import com.crypto.repository.CoinWhiteListEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;

/**
 * Created by Semih, 24.09.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CoinWhitelistSave implements Predicate<CoinWhitelistRequest> {

    private final CoinWhiteListControl coinWhiteListControl;

    private final CoinWhiteListEntityRepository coinWhiteListEntityRepository;

    @Override
    public boolean test(CoinWhitelistRequest coinWhitelistRequest) {

        coinWhiteListControl.accept(coinWhitelistRequest.getCoin());

        final CoinWhiteListEntity entity = CoinWhiteListEntity.builder()
                .coinId(coinWhitelistRequest.getCoin())
                .isActive(Boolean.TRUE)
                .build();

        coinWhiteListEntityRepository.save(entity);

        log.info("Whitelist save operation successfully.");

        return Boolean.TRUE;
    }
}
