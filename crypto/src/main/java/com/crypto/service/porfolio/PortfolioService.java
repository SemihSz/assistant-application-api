package com.crypto.service.porfolio;

import com.crypto.entity.UserTotalCoins;
import com.crypto.model.coingecko.CoingeckoModel;
import com.crypto.model.coingecko.CoingeckoPriceModel;
import com.crypto.model.input.GetCoinInfoListInput;
import com.crypto.model.portfolio.UserPortfolio;
import com.crypto.model.portfolio.UserSpecificCoin;
import com.crypto.model.portfolio.UserSpecificCoinInfo;
import com.crypto.repository.UserTotalCoinsRepository;
import com.crypto.request.porfolio.UserPortfolioRequest;
import com.crypto.service.coingecko.getcoinslist.FilterCoinlistInfoService;
import com.crypto.service.coingecko.getcoinslist.GetCoinInfoListService;
import com.crypto.type.Currency;
import com.crypto.utils.CalculateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author semih on Haziran, 2022, 6.06.2022, 22:23:26
 */
@Service
@RequiredArgsConstructor
public class PortfolioService extends PortfolioAbstraction {

	private final UserTotalCoinsRepository userTotalCoinsRepository;

	private final GetCoinInfoListService getCoinInfoListService;

	private final FilterCoinlistInfoService filterCoinlistInfoService;

	@Override
	public UserPortfolio showUserPortfolio(UserPortfolioRequest request) {

		final List<UserTotalCoins> userTotalCoinsList = userTotalCoinsRepository.getUserTotalCoins(request.getUserId());
		final Set<String> userCoinNameList = new HashSet<>();

		userTotalCoinsList.forEach(t -> {
			userCoinNameList.add(t.coinId);
		});


		final GetCoinInfoListInput input = GetCoinInfoListInput.builder()
				.numberOfCoins(2000)
				.currency(Currency.USD)
				.numberOfPage(10)
				.build();

		final List<CoingeckoModel> coingeckoModels = getCoinInfoListService.apply(input);


// TODO Ath üzerinden hesaplamasını yap
		final Map<String, UserSpecificCoin> userPortfolio = new HashMap<>();
		userCoinNameList.forEach(t -> {
			final List<UserTotalCoins> getUserCoinListFilter = userTotalCoinsList.stream().filter(s ->s.coinId.equals(t)).collect(Collectors.toList());

			final Optional<CoingeckoModel> model = coingeckoModels.stream().filter(s ->s.getId().equals(t)).findFirst();
			final CoingeckoPriceModel priceModel = model.get().getCoingeckoPriceModel();

			if (getUserCoinListFilter.size() > 1) {

				final List<UserSpecificCoinInfo> coinInfoList = new ArrayList<>();
				getUserCoinListFilter.forEach(f -> {
					final UserSpecificCoinInfo userSpecificCoin = UserSpecificCoinInfo.builder()
							.numberOfCoins(f.getNumberOfCoins().toString())
							.percentageOfGainOrLoss(f.getBuyCurrentPriceChange())
							.purchasePrice(f.getCoinBuyPrice())
							.thatDayPrice(f.getCoinCurrentPrice())
							.build();
					coinInfoList.add(userSpecificCoin);
				});
				final BigDecimal totalPrice = getUserCoinListFilter.stream().map(UserTotalCoins::getCoinBuyPrice).reduce(BigDecimal::add).get();
				final BigDecimal currentPrice = priceModel.getPrice();
				final BigDecimal averagePrice =totalPrice.divide(BigDecimal.valueOf(2)).setScale(2, RoundingMode.CEILING);
				final BigDecimal averagePercentageOfGainAndLoss = CalculateUtils.calculatePercentageOfGainOrLoss(currentPrice, averagePrice);
				final BigDecimal athPercentageGainOrLoss = CalculateUtils.calculatePercentageOfGainOrLoss(averagePrice, priceModel.getAth());
				final UserSpecificCoin specificCoin = UserSpecificCoin.builder()
						.averagePercentageGainOrLoss(averagePercentageOfGainAndLoss)
						.averagePurchase(averagePrice)
						.userSpecificCoinInfos(coinInfoList)
						.athPercentageGainOrLoss(athPercentageGainOrLoss)
						.build();
				userPortfolio.put(t, specificCoin);
			}

		});

		return UserPortfolio.builder().userPortfolio(userPortfolio).build();
	}
}
