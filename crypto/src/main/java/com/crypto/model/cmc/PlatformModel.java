package com.crypto.model.cmc;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author semih on Haziran, 2021, 13.06.2021, 20:38:54
 */
@Getter
@Setter
@Builder
public class PlatformModel {

	private String name;

	private String tokenAddress;
}
