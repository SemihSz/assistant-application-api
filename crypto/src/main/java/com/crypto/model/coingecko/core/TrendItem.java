package com.crypto.model.coingecko.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrendItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("symbol")
	private String symbol;

	@JsonProperty("score")
	private int score;

	@JsonProperty("coin_id")
	private int coinId;

	@JsonProperty("name")
	private String name;

	@JsonProperty("market_cap_rank")
	private int marketCapRank;

	@JsonProperty("id")
	private String id;

	@JsonProperty("price_btc")
	private BigDecimal priceBtc;

}
