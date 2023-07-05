package com.crypto.request.coingecko;

import com.crypto.type.Currency;
import lombok.Builder;
import lombok.Getter;

/**
 * @author semih on Eylül, 2021, 18.09.2021, 19:17:25
 */
@Getter
@Builder
public class CoreMarketChartRequest {

	private String id;

	private int numberOfDays;

	private Currency currency;

	private String interval;

}
