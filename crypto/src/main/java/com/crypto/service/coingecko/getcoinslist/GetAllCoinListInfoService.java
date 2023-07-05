package com.crypto.service.coingecko.getcoinslist;

import com.crypto.entity.AllCoinsInfoEntity;
import com.crypto.model.coingecko.coin_info.AllCoinListInfoModel;
import com.crypto.repository.AllCoinsInfoEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Created by Semih, 10.04.2022
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GetAllCoinListInfoService implements Supplier<List<AllCoinListInfoModel>> {

    private final AllCoinsInfoEntityRepository allCoinsInfoEntityRepository;

    @Override
    public List<AllCoinListInfoModel> get() {

        final List<AllCoinsInfoEntity> getAllCoins = allCoinsInfoEntityRepository.findAll();

        final List<AllCoinListInfoModel> response = new ArrayList<>();
        getAllCoins.forEach(t -> {

            final AllCoinListInfoModel model = AllCoinListInfoModel.builder()
                    .coinId(t.getCoinId())
                    .coinName(t.getCoinName())
                    .build();
            response.add(model);

        });

        return response;
    }
}
