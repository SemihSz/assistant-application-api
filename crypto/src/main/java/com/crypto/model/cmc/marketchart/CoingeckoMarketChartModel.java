package com.crypto.model.cmc.marketchart;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * @author semih on Eylül, 2021, 18.09.2021, 19:51:31
 */
@Getter
@Builder
public class CoingeckoMarketChartModel {

	public CoingeckoMarkerChartModelAll latest;

//	public List<PricesMarketChart> prices;
//
//	public List<MarketCapsChart> market_caps;
//
//	public List<TotalVolumesChart> total_volumes;

	public List<CoingeckoMarkerChartModelAll> allInList;


}
