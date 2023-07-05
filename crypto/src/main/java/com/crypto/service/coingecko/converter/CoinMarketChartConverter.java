package com.crypto.service.coingecko.converter;

import com.crypto.model.cmc.marketchart.*;
import com.crypto.model.coingecko.core.CoinMarketChartModel;
import com.assistant.commonapi.task.SimpleTask;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author semih on Eylül, 2021, 18.09.2021, 19:50:05
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CoinMarketChartConverter implements SimpleTask<CoinMarketChartModel, CoingeckoMarketChartModel> {

	@Override
	public CoingeckoMarketChartModel apply(CoinMarketChartModel coingeckoMarketChatModel) {

		final List<PricesMarketChart> pricesList = new ArrayList<>();
		final List<TotalVolumesChart> volumeList = new ArrayList<>();
		final List<MarketCapsChart> marketCapList = new ArrayList<>();

		coingeckoMarketChatModel.prices.forEach(t -> {

			final Timestamp stamp = new Timestamp(Long.parseLong(t[0].toString()));
			final PricesMarketChart pricesMarketChart = PricesMarketChart.builder()
					.price(BigDecimal.valueOf((Double) t[1]))
					.time(stamp.toLocalDateTime())
					.build();

			pricesList.add(pricesMarketChart);
		});

		coingeckoMarketChatModel.total_volumes.forEach(t -> {

			final Timestamp stamp = new Timestamp(Long.parseLong(t[0].toString()));
			final TotalVolumesChart totalVolumesChart = TotalVolumesChart.builder()
					.volume(BigDecimal.valueOf((Double) t[1]))
					.time(stamp.toLocalDateTime())
					.build();

			volumeList.add(totalVolumesChart);
		});

		coingeckoMarketChatModel.market_caps.forEach(t -> {

			final Timestamp stamp = new Timestamp(Long.parseLong(t[0].toString()));
			final MarketCapsChart marketCapsChart = MarketCapsChart.builder()
					.time(stamp.toLocalDateTime())
					.marketCap(BigDecimal.valueOf((Double) t[1]))
					.build();

			marketCapList.add(marketCapsChart);
		});


		final List<CoingeckoMarkerChartModelAll> allList = new ArrayList<>();

		pricesList.forEach(t -> {

			final MarketCapsChart marketCapsChart = marketCapList.stream().filter(m -> m.getTime().equals(t.getTime())).findFirst().get();
			final TotalVolumesChart totalVolumesChart = volumeList.stream().filter(v -> v.getTime().equals(t.getTime())).findFirst().get();
			final CoingeckoMarkerChartModelAll allResponse = CoingeckoMarkerChartModelAll.builder()
					.time(t.getTime())
					.price(t.getPrice())
					.volume(totalVolumesChart.getVolume())
					.marketCap(marketCapsChart.getMarketCap())
					.build();
			allList.add(allResponse);
		});

		allList.sort(Comparator.comparing(CoingeckoMarkerChartModelAll::getTime).reversed());
		final CoingeckoMarkerChartModelAll latest = allList.get(0);

		return CoingeckoMarketChartModel.builder()
//				.market_caps(marketCapList)
//				.prices(pricesList)
//				.total_volumes(volumeList)
				.allInList(allList)
				.latest(latest)
				.build();
	}
}
