package com.crypto.model.coingecko.core;

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
 * Created by Semih, 18.06.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoreCoingeckoCoinsMarket implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    private String id;

    @JsonProperty("symbol")
    private String symbol;

    @JsonProperty("name")
    private String name;

    @JsonProperty("image")
    private String image;

    @JsonProperty("current_price")
    private BigDecimal current_price;

    @JsonProperty("market_cap")
    private BigDecimal marketCap;

    @JsonProperty("market_cap_rank")
    private int marketCapRank;

    @JsonProperty("fully_diluted_valuation")
    private long fullyDilutedValuation;

    @JsonProperty("total_volume")
    private BigDecimal totalVolume;

    @JsonProperty("high_24h")
    private BigDecimal high24h;

    @JsonProperty("low_24h")
    private BigDecimal low24h;

    @JsonProperty("price_change_24h")
    private BigDecimal priceChange24h;

    @JsonProperty("price_change_percentage_24h")
    private Double priceChangePercentage24h;

    @JsonProperty("market_cap_change_24h")
    private BigDecimal marketCapChange24h;

    @JsonProperty("market_cap_change_percentage_24h")
    private BigDecimal marketCapChangePercentage24h;

    @JsonProperty("circulating_supply")
    private BigDecimal circulatingSupply;

    @JsonProperty("total_supply")
    private BigDecimal totalSupply;

    @JsonProperty("max_supply")
    private BigDecimal maxSupply;

    @JsonProperty("ath")
    private BigDecimal ath;

    @JsonProperty("ath_change_percentage")
    private double athChangePercentage;

    @JsonProperty("ath_date")
    private Date athDate;

    @JsonProperty("atl")
    private double atl;

    @JsonProperty("atl_change_percentage")
    private double atlChangePercentage;

    @JsonProperty("atl_date")
    private Date atlDate;

    @JsonProperty("roi")
    private CoreCoingeckoRoi roi;

    @JsonProperty("last_updated")
    private Date lastUpdated;
}
