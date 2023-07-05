package com.crypto.service.aspect;

import com.crypto.service.auth.IsAuthenticationWhiteListService;
import com.crypto.service.auth.IsCryptoAuthenticationTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author semih on Haziran, 2022, 6.06.2022, 12:05:27
 */

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class CryptoSecurityAspect {

	public static final String WHITE_LIST_TOKEN = "coinWhitelistToken";

	public static final String CRYPTO_AUTH_TOKEN = "cryptoAuthToken";

	public static final String USER_ID = "userId";

	private final IsAuthenticationWhiteListService isAuthenticationWhiteListService;

	private final IsCryptoAuthenticationTokenService isCryptoAuthenticationTokenService;

	// TODO ALl restcontroller do this operations
	@Pointcut("within(@org.springframework.web.bind.annotation.RestController *) && execution(* com.crypto.controller.CryptoController.*(..))")
	public void allMethods() {
	}

 /*   @Pointcut("within(@org.springframework.web.bind.annotation.RestController *) && execution(* com.crypto.controller.*..*(..))")
    public void allMethods() {
    }*/


	@Around(" allMethods()")
	public Object validateAspect(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

		final HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		final MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
		final Method method = signature.getMethod();

		final String coinWhitelistToken = request.getHeader(WHITE_LIST_TOKEN);
		final String cryptoAuthToken = request.getHeader(CRYPTO_AUTH_TOKEN);
		final String userId = request.getHeader(USER_ID);

		if (StringUtils.isNotBlank(coinWhitelistToken)) {
			isAuthenticationWhiteListService.accept(coinWhitelistToken);
		}

		if (StringUtils.isNotBlank(cryptoAuthToken) && StringUtils.isNotBlank(userId)) {
			isCryptoAuthenticationTokenService.accept(userId, cryptoAuthToken);
		}

		return proceedingJoinPoint.proceed();
	}
}

