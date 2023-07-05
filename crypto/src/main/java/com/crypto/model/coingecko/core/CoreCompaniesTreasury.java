
package com.crypto.model.coingecko.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoreCompaniesTreasury implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("companies")
    private List<Company> mCompanies;

    @JsonProperty("market_cap_dominance")
    private BigDecimal mMarketCapDominance;

    @JsonProperty("total_holdings")
    private BigDecimal mTotalHoldings;

    @JsonProperty("total_value_usd")
    private BigDecimal mTotalValueUsd;


}
