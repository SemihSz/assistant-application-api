package com.crypto.service.auth;

import com.crypto.entity.ConfigEntity;
import com.crypto.exception.WhitelistAuthenticationTokenException;
import com.crypto.repository.ConfigRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * Created by Semih, 24.09.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class IsAuthenticationWhiteListService implements Consumer<String> {

    private final ConfigRepository configRepository;

    private static final String COIN_WHITE_LIST_CODE = "COIN_WHITE_LIST";

    @Override
    public void accept(String s) {

        final ConfigEntity config = configRepository.findById(COIN_WHITE_LIST_CODE).get();

        if (Objects.nonNull(config)) {

            if (s.equals(config.getValue())) {
                log.info("Whitelist token is authenticated.");
            }
            else {
                throw new WhitelistAuthenticationTokenException("Authentication token wrong");
            }
        }
    }
}
