package com.crypto.service.coingecko.getcoinslist;

import com.crypto.entity.CoinsEntity;
import com.crypto.repository.CoinsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Semih, 24.09.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class GetCoinListService extends GetCoinList {

    private final CoinsRepository coinsRepository;

    @Override
    public List<CoinsEntity> getterCoinList() {
        return coinsRepository.findAll();
    }

    @Override
    public CoinsEntity getterCoin(String name) {
        return coinsRepository.findById(name).get();
    }
}
