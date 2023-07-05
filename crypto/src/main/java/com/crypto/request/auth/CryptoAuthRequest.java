package com.crypto.request.auth;

import lombok.Getter;
import lombok.Setter;

/**
 * @author semih on Eylül, 2021, 25.09.2021, 21:31:01
 */

@Getter
@Setter
public class CryptoAuthRequest {

	private String cryptoAuthTokenHash;

	private String userId;
}
