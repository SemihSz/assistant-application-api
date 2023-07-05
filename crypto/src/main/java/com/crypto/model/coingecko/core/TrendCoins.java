package com.crypto.model.coingecko.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author semih on Eylül, 2021, 18.09.2021, 17:32:59
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrendCoins implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("item")
	private TrendItem item;
}
