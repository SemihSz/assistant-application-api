package com.earthquake.api.service.cache;

import com.earthquake.api.constant.Constant;
import com.earthquake.api.exception.TokenException;
import com.earthquake.api.model.world.WorldEarthQuakeModel;
import com.earthquake.api.request.world.WorldEarthQuakeRequest;
import com.earthquake.api.response.world.WorldEarthQuakeGenericResponse;
import com.earthquake.api.service.token.TokenService;
import com.assistant.commonapi.task.SimpleTask;
import com.earthquake.api.type.InstitutionType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Objects;

/**
 * Created by Semih, 19.02.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class WorldEarthQuakeCacheService implements SimpleTask<WorldEarthQuakeRequest, WorldEarthQuakeGenericResponse<WorldEarthQuakeModel>> {

    private final CacheService<String, WorldEarthQuakeGenericResponse<WorldEarthQuakeModel>> cacheService;

    private final TokenService tokenService;

    private final MessageSource messageSource;

    @Override
    public WorldEarthQuakeGenericResponse<WorldEarthQuakeModel> apply(WorldEarthQuakeRequest request) {

        if (Objects.nonNull(request.getToken())) {
            if (InstitutionType.USGS.equals(request.getInstitutionType())) {
                if (tokenService.controlTokenActive(request.getToken()) && InstitutionType.USGS.equals(request.getInstitutionType())) {
                    return cacheService.get(request.getToken());
                }
            } else {
                log.error("Institution type must be USGS");
            }
        }
        else {
            throw new TokenException(messageSource.getMessage(Constant.Message.TOKEN_EXPIRE, null, Locale.ENGLISH));
        }

        return null;
    }
}
