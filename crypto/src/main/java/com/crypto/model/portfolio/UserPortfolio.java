package com.crypto.model.portfolio;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * @author semih on Haziran, 2022, 6.06.2022, 22:04:17
 */
@Getter
@Setter
@Builder
public class UserPortfolio {

	private Map<String, UserSpecificCoin> userPortfolio;

}
