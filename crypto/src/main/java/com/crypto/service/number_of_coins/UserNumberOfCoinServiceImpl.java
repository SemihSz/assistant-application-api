package com.crypto.service.number_of_coins;

import com.crypto.entity.UserTotalCoins;
import com.crypto.model.coingecko.CoingeckoModel;
import com.crypto.model.coingecko.CoingeckoPriceModel;
import com.crypto.model.coingecko.coin_info.AllCoinListInfoModel;
import com.crypto.model.input.FilterAllCoinsInput;
import com.crypto.model.input.GetCoinInfoListInput;
import com.crypto.repository.UserTotalCoinsRepository;
import com.crypto.request.number_of_coins.UserAddNumberOfCoins;
import com.crypto.service.coingecko.getcoinslist.FilterCoinlistInfoService;
import com.crypto.service.coingecko.getcoinslist.GetCoinInfoListService;
import com.crypto.type.Currency;
import com.crypto.utils.CalculateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by Semih, 25.04.2022
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserNumberOfCoinServiceImpl extends UserNumberOfCoinService {

    private final UserTotalCoinsRepository userTotalCoinsRepository;

    private final FilterCoinlistInfoService filterCoinlistInfoService;

    private final GetCoinInfoListService getCoinInfoListService;

    @Override
    public Boolean addUserNewNumberOfCoins(UserAddNumberOfCoins request) {

        final FilterAllCoinsInput filterAllCoinsInput = FilterAllCoinsInput.builder()
                .coinId(request.getCoinId())
                .coinName(request.getCoinName())
                .build();

        final AllCoinListInfoModel infoModel = filterCoinlistInfoService.apply(filterAllCoinsInput);

        if (Objects.nonNull(infoModel)) {

            final GetCoinInfoListInput input = GetCoinInfoListInput.builder()
                    .numberOfCoins(2000)
                    .currency(Currency.USD)
                    .numberOfPage(10)
                    .build();

            final List<CoingeckoModel> coingeckoModels = getCoinInfoListService.apply(input);

            final Optional<CoingeckoModel> model = coingeckoModels.stream().filter(t ->t.getId().equals(infoModel.getCoinId())).findFirst();

            model.ifPresent(coingeckoModel -> log.info(coingeckoModel.toString()));

            final CoingeckoPriceModel priceModel = model.get().getCoingeckoPriceModel();

            // TODO kullanıcı yeniden aynı coin eklerse bununda kayıt altına alınması lazım.

            final UserTotalCoins userTotalCoins = UserTotalCoins.builder()
                    .userId(request.getUserId())
                    .coinId(infoModel.getCoinId())
                    .coinName(infoModel.getCoinName())
                    .numberOfCoins(request.getNumberOfCoins())
                    .coinBuyPrice(request.getCoinBuyPrice())
                    .coinCurrentPrice(priceModel.getPrice())
                    .currenTDate(LocalDateTime.now())
                    .buyCurrentPriceChange(CalculateUtils.calculatePercentageOfGainOrLoss(priceModel.getPrice(), request.getCoinBuyPrice()))
                    .build();

            userTotalCoinsRepository.save(userTotalCoins);

            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }
}
