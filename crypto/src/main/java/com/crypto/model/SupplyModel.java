package com.crypto.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author semih on Haziran, 2021, 13.06.2021, 20:15:29
 */

@Getter
@Setter
@Builder
public class SupplyModel {

	private BigDecimal maxSupply;

	private BigDecimal circulatingSupply;

	private BigDecimal totalSupply;
}
