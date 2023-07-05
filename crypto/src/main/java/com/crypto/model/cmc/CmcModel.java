package com.crypto.model.cmc;

import com.crypto.model.SupplyModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author semih on Haziran, 2021, 13.06.2021, 20:41:36
 */
@Getter
@Setter
@Builder
public class CmcModel {

	private Long id;

	private String name;

	private SupplyModel supply;

	private CmcPriceModel price;

	private PlatformModel platform;
}
