
package com.crypto.model.coingecko.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("country")
    private String mCountry;

    @JsonProperty("name")
    private String mName;

    @JsonProperty("percentage_of_total_supply")

    private BigDecimal mPercentageOfTotalSupply;

    @JsonProperty("symbol")
    private String mSymbol;

    @JsonProperty("total_current_value_usd")
    private BigDecimal mTotalCurrentValueUsd;

    @JsonProperty("total_entry_value_usd")
    private BigDecimal mTotalEntryValueUsd;

    @JsonProperty("total_holdings")
    private BigDecimal mTotalHoldings;


}
