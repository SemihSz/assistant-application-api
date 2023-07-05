package com.crypto.model.cmc.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author semih on Haziran, 2021, 13.06.2021, 19:53:19
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoreCmcPrice implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("price")
	private BigDecimal price;

	@JsonProperty("volume_24h")
	private BigDecimal volume24h;

	@JsonProperty("percent_change_1h")
	private Double percentChange1h;

	@JsonProperty("percent_change_24h")
	private Double percentChange24h;

	@JsonProperty("percent_change_7d")
	private Double percentChange7d;

	@JsonProperty("percent_change_30d")
	private Double percentChange30d;

	@JsonProperty("percent_change_60d")
	private Double percentChange60d;

	@JsonProperty("percent_change_90d")
	private Double percentChange90d;

	@JsonProperty("market_cap")
	private BigDecimal marketCap;

	@JsonProperty("last_updated")
	private Date lastUpdated;

}
