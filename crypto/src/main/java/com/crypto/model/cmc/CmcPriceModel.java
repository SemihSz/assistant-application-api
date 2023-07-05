package com.crypto.model.cmc;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author semih on Haziran, 2021, 13.06.2021, 20:22:44
 */
@Getter
@Setter
@Builder
public class CmcPriceModel {

	private BigDecimal price;

	private BigDecimal volume24h;

	private Double percentChange1h;

	private Double percentChange24h;

	private Double percentChange7d;

	private Double percentChange30d;

	private Double percentChange60d;

	private BigDecimal marketCap;

	private Date lastUpdated;

}
