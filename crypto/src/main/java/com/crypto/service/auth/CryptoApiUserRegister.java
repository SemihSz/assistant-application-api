package com.crypto.service.auth;

import com.crypto.entity.CryptoTokenAuthEntity;
import com.crypto.exception.AuthenticationException;
import com.crypto.repository.CryptoTokenAuth;
import com.crypto.request.auth.CryptoAuthRequest;
import com.assistant.commonapi.task.SimpleTask;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;


/**
 * Created by Semih, 26.09.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Slf4j
@Service
@AllArgsConstructor
public class CryptoApiUserRegister implements SimpleTask<CryptoAuthRequest, Boolean> {

    private final CryptoTokenAuth cryptoTokenAuth;

    private static final Long NUMBER_OF_USAGE = 10000L;

    private final GenerateHashingSHA1Service generateHashingSHA1Service;

    @SneakyThrows
    @Override
    public Boolean apply(CryptoAuthRequest cryptoAuthRequest) {

        if (Objects.isNull(cryptoAuthRequest.getUserId()) || Objects.isNull(cryptoAuthRequest.getCryptoAuthTokenHash())) {
            throw new AuthenticationException("Authentication Exception");
        }

        final CryptoTokenAuthEntity entity = CryptoTokenAuthEntity.builder()
                .userId(cryptoAuthRequest.getUserId())
                .hashToken(generateHashingSHA1Service.generateHash(cryptoAuthRequest.getCryptoAuthTokenHash()))
                .createdDate(LocalDateTime.now())
                .numberOfUsage(NUMBER_OF_USAGE)
                .build();

        cryptoTokenAuth.save(entity);

        return Boolean.TRUE;
    }
}
