package com.crypto.service.coingecko.getcoinslist;

import com.crypto.model.coingecko.coin_info.AllCoinListInfoModel;
import com.crypto.model.input.FilterAllCoinsInput;
import com.assistant.commonapi.task.SimpleTask;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


/**
 * Created by Semih, 25.04.2022
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FilterCoinlistInfoService implements SimpleTask<FilterAllCoinsInput,AllCoinListInfoModel> {

    private final GetAllCoinListInfoService getAllCoinListInfoService;

    @Override
    public AllCoinListInfoModel apply(FilterAllCoinsInput filterAllCoinsInput) {

        final List<AllCoinListInfoModel> infoModelList = getAllCoinListInfoService.get();
        if (Objects.nonNull(filterAllCoinsInput.getCoinId())) {

            return infoModelList.stream().filter(t -> t.getCoinId().equals(filterAllCoinsInput.getCoinId())).findFirst().orElse(null);
        }
        else {

            if (Objects.nonNull(filterAllCoinsInput.getCoinName())) {

                return infoModelList.stream().filter(t -> t.getCoinName().equals(filterAllCoinsInput.getCoinName())).findFirst().orElse(null);

            }
        }
        return null;
    }
}
