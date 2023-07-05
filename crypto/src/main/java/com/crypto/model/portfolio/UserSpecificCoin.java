package com.crypto.model.portfolio;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author semih on Haziran, 2022, 6.06.2022, 22:14:30
 */
@Getter
@Setter
@Builder
public class UserSpecificCoin {

	private List<UserSpecificCoinInfo> userSpecificCoinInfos;

	private BigDecimal averagePurchase;

	private BigDecimal averagePercentageGainOrLoss;

	private BigDecimal athPercentageGainOrLoss;

}
