package com.crypto.model.coingecko;

import com.crypto.model.SupplyModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author semih on Haziran, 2021, 19.06.2021, 22:32:13
 */
@Getter
@Setter
@Builder
public class CoingeckoModel {

	private int marketCapRank;

	private String id;

	private String name;

	private CoingeckoPriceModel coingeckoPriceModel;

	private SupplyModel supplyModel;

	private Date lastUpdated;

}
