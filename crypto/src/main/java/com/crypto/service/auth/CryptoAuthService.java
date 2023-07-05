package com.crypto.service.auth;

import com.crypto.request.auth.CryptoAuthRequest;
import com.crypto.request.auth.CryptoAuthResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author semih on Mayıs, 2022, 29.05.2022, 21:14:27
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class CryptoAuthService {

	private final CryptoApiUserRegister userRegister;

	private final GenerateUserService generateUserService;


	public Boolean registerAuth(CryptoAuthRequest request) {

		return userRegister.apply(request);
	}

	public CryptoAuthResponse generateUser() {

		return generateUserService.get();
	}
}
