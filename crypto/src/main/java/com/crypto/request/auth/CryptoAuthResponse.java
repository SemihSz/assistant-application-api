package com.crypto.request.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author semih on Mayıs, 2022, 29.05.2022, 21:13:10
 */
@Getter
@Setter
@Builder
public class CryptoAuthResponse {

	private String userId;

	private String authToken;
}
