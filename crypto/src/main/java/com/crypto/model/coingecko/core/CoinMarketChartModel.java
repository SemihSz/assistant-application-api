package com.crypto.model.coingecko.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author semih on Eylül, 2021, 18.09.2021, 19:13:25
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoinMarketChartModel  implements Serializable {

	private static final long serialVersionUID = 1L;

	public List<Object[]> prices;

	public List<Object[]> market_caps;

	public List<Object[]> total_volumes;
}
