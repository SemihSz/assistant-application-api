package com.earthquake.api.service.token;

import com.earthquake.api.constant.Constant;
import com.earthquake.api.request.TokenRequest;
import com.earthquake.api.service.cache.CacheService;
import com.earthquake.api.service.logger.EarthQuakeLoggerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author semih on Eylül, 2020, 26.09.2020, 18:30:52
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TokenService {

	private final Random random = new SecureRandom();

	private final EarthQuakeLoggerService loggerService;

	private final CacheService<String, List<String>> tokenCache;

	public String generateToken(TokenRequest request) {

		loggerService.requestLogging(request, Constant.Request.TOKEN);

		List<String> tokenList = new ArrayList<>();
		StringBuilder token = new StringBuilder();

		for (int i = 0; i < Constant.TOKEN_SIZE; i++) {

			token.append(Constant.ALPHABET.charAt(random.nextInt(Constant.ALPHABET.length())));

		}
		token.append((request.getInstitutionType().toString()));
		if (tokenCache.get("token") != null) {
			tokenList = tokenCache.get("token");
			tokenList.add(token.toString());
		}
		else {
			tokenList.add(token.toString());
		}
		tokenCache.put("token", tokenList);
		return new String(token);
	}

	public String generateId() {

		StringBuilder token = new StringBuilder();

		for (int i = 0; i < 11; i++) {

			token.append(Constant.ALPHABET.charAt(random.nextInt(Constant.ALPHABET.length())));

		}
		return new String(token);
	}

	public Boolean controlTokenActive(String token) {

		String tokenList;
		if (tokenCache.get("token") != null) {
			tokenList = tokenCache.get("token").stream().filter(t -> t.equals(token)).findFirst().orElse(null);
		}
		else {
			tokenList = null;
		}
		return tokenList != null;
	}
}
