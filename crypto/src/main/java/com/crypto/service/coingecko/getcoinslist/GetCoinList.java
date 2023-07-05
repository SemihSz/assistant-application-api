package com.crypto.service.coingecko.getcoinslist;

import com.crypto.entity.CoinsEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Semih, 24.09.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Service
public abstract class GetCoinList {

    public abstract List<CoinsEntity> getterCoinList();

    public abstract CoinsEntity getterCoin(String name);
}
