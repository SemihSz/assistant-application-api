package com.crypto.model.coingecko;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author semih on Haziran, 2021, 19.06.2021, 22:28:02
 */
@Getter
@Setter
@Builder
public class CoingeckoPriceModel {

	private BigDecimal price;

	private BigDecimal priceHigh24h;

	private BigDecimal priceLow24h;

	private Double priceChange24h;

	private Double priceChangePercentage24h;

	private BigDecimal marketCap;

	private BigDecimal marketCapChange24h;

	private BigDecimal marketCapChangePercentage24h;

	private BigDecimal ath;

	private BigDecimal totalVolume;
}
