package com.crypto.model.cmc.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author semih on Haziran, 2021, 12.06.2021, 22:54:06
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoreCmcInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	private Long id;

	@JsonProperty("name")
	private String name;

	@JsonProperty("symbol")
	private String symbol;

	@JsonProperty("slug")
	private String slug;

	@JsonProperty("num_market_pairs")
	private String numMarketPairs;

	@JsonProperty("date_added")
	private LocalDateTime dateAdded;

	@JsonProperty("tags")
	private List<String> tags;

	@JsonProperty("max_supply")
	private BigDecimal maxSupply;

	@JsonProperty("circulating_supply")
	private BigDecimal circulatingSupply;

	@JsonProperty("total_supply")
	private BigDecimal totalSupply;

	@JsonProperty("platform")
	private CoreCmcPlatform platform;

	@JsonProperty("cmc_rank")
	private Integer cmcRank;

	@JsonProperty("last_updated")
	private LocalDateTime lastUpdated;

	@JsonProperty("quote")
	private CoreCmcQuote quote;


}
