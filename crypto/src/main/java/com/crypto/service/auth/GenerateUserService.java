package com.crypto.service.auth;

import com.crypto.entity.CryptoTokenAuthEntity;
import com.crypto.repository.CryptoTokenAuth;
import com.crypto.request.auth.CryptoAuthResponse;
import com.crypto.utils.TokenGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDateTime;
import java.util.function.Supplier;

/**
 * @author semih on Mayıs, 2022, 29.05.2022, 21:17:41
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GenerateUserService implements Supplier<CryptoAuthResponse> {

	private final static int AUTH_TOKEN_LENGTH = 32;

	private final static int USER_LENGTH = 12;

	private final TokenGenerator tokenGenerator;

	private final GenerateHashingSHA1Service generateHashingSHA1Service;

	private final CryptoTokenAuth cryptoTokenAuth;

	@Override
	public CryptoAuthResponse get() {

		final String userId = tokenGenerator.generateRandomString(USER_LENGTH);
		final String authToken = tokenGenerator.generateRandomString(AUTH_TOKEN_LENGTH);

		try {
			final CryptoTokenAuthEntity entity = CryptoTokenAuthEntity.builder()
					.userId(userId)
					.hashToken(generateHashingSHA1Service.generateHash(authToken))
					.createdDate(LocalDateTime.now())
					.numberOfUsage(10000L)
					.build();
			cryptoTokenAuth.save(entity);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			log.error(e.getMessage());
		}

		return CryptoAuthResponse.builder().authToken(authToken).userId(userId).build();
	}
}
