package com.crypto.model.coingecko.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoingeckoTrendModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("coins")
	private List<TrendCoins> trendCoins;

	@JsonProperty("exchanges")
	private List<Object> exchanges;
}
