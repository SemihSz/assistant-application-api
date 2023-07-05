package com.crypto.service.auth;

import com.crypto.entity.CryptoTokenAuthEntity;
import com.crypto.exception.AuthenticationException;
import com.crypto.repository.CryptoTokenAuth;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.function.BiConsumer;

/**
 * Created by Semih, 2.10.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Slf4j
@Service
@AllArgsConstructor
public class IsCryptoAuthenticationTokenService implements BiConsumer<String, String> {

    private final CryptoTokenAuth cryptoTokenAuth;

    private final GenerateHashingSHA1Service generateHashingSHA1Service;

    @SneakyThrows
    @Override
    public void accept(String userId, String cryptoAuthToken) {

        final CryptoTokenAuthEntity cryptoTokenAuthEntity = cryptoTokenAuth.botUserControl(userId);
        if (Objects.nonNull(cryptoTokenAuthEntity)) {

            final boolean matchedToken = generateHashingSHA1Service.validateHash(cryptoAuthToken, cryptoTokenAuthEntity.getHashToken());
            if (matchedToken) {
                log.info("Crypto api is active...");
            }
            else {
                throw new AuthenticationException("Crypto api authentication token wrong");
            }
        }
        else {
            throw new AuthenticationException("User not exist");
        }
    }
}
