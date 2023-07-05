package com.crypto.service.coingecko.whitelist;

import com.crypto.entity.CoinWhiteListEntity;
import com.crypto.repository.CoinWhiteListEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Created by Semih, 7.04.2022
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GetWhiteListInfoService implements Supplier<List<CoinWhiteListEntity>> {

    private final CoinWhiteListEntityRepository coinWhiteListEntityRepository;

    @Override
    public List<CoinWhiteListEntity> get() {

        List<CoinWhiteListEntity> coinWhiteListEntityList = new ArrayList<>();
        coinWhiteListEntityRepository.findAll().forEach(coinWhiteListEntityList::add);

        return coinWhiteListEntityList;
    }
}
