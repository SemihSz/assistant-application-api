package com.crypto.model.portfolio;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author semih on Haziran, 2022, 6.06.2022, 22:07:14
 */
@Getter
@Setter
@Builder
public class UserSpecificCoinInfo {

	private String numberOfCoins;

	private BigDecimal purchasePrice;

	private BigDecimal thatDayPrice;

	private BigDecimal percentageOfGainOrLoss;

}
