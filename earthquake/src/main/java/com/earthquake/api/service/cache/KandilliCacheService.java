package com.earthquake.api.service.cache;

import com.earthquake.api.constant.Constant;
import com.earthquake.api.exception.TokenException;
import com.earthquake.api.model.EarthQuakeInfo;
import com.earthquake.api.request.EarthQuakeRequest;
import com.earthquake.api.response.EarthQuakeResponse;
import com.earthquake.api.service.cache.model.GenericCacheModel;
import com.earthquake.api.service.filter.AllEarthQuakeListService;
import com.earthquake.api.service.token.TokenService;
import com.earthquake.api.service.usage.UsageCountService;
import com.assistant.commonapi.task.SimpleTask;
import com.earthquake.api.type.InstitutionType;
import com.earthquake.api.type.ServiceUsageStatusType;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 * Created by Semih, 7.11.2020
 * <p>github: <a href="https://github.com/SemihSz ">
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class KandilliCacheService implements SimpleTask<EarthQuakeRequest, EarthQuakeResponse> {

    private final AllEarthQuakeListService coreKandilliService;

    private final CacheService<String, GenericCacheModel<List<EarthQuakeInfo>>> cacheService;

    private final TokenService tokenService;

    private final MessageSource messageSource;

    private final UsageCountService usageCountService;

    @SneakyThrows
    @Override
    public EarthQuakeResponse apply(EarthQuakeRequest request) {

        try {
            if (Objects.nonNull(request.getToken())) {

                if (InstitutionType.KANDILLI.equals(request.institutionType)) {

                    if (tokenService.controlTokenActive(request.getToken()) && InstitutionType.KANDILLI.equals(request.institutionType)) {
                        final GenericCacheModel<List<EarthQuakeInfo>> getCacheInfo = cacheService.get(request.getToken());
                        if (Objects.nonNull(getCacheInfo) && InstitutionType.KANDILLI.equals(getCacheInfo.getInstitutionType())) {
                            usageCountService.accept(Constant.Request.ALL_LIST, ServiceUsageStatusType.SUCCESS);
                            return EarthQuakeResponse.builder().quakeInfoList(getCacheInfo.getData()).build();
                        }

                        else {

                            final EarthQuakeResponse coreResponse = EarthQuakeResponse.builder()
                                    .quakeInfoList(coreKandilliService.getAllEarthQuakes()).build();

                            GenericCacheModel<List<EarthQuakeInfo>> cacheModel = new GenericCacheModel<>();
                            cacheModel.setInstitutionType(request.institutionType);
                            cacheModel.setData(coreResponse.getQuakeInfoList());
                            log.info("Start kandilli info to cache");
                            cacheService.put(request.getToken(), cacheModel);
                            log.info("Finish kandilli info to cache");
                            usageCountService.accept(Constant.Request.ALL_LIST, ServiceUsageStatusType.SUCCESS);
                            return coreResponse;
                        }
                    }
                    else {
                        throw new TokenException(messageSource.getMessage(Constant.Message.TOKEN_EXPIRE, null, Locale.ENGLISH));
                    }
                }
                else {
                    log.error("Institution type must be KANDILLI");
                }
            }
        } catch (Exception e) {
            usageCountService.accept(Constant.Request.ALL_LIST, ServiceUsageStatusType.FAIL);
            log.error("Error kandilli info to cache");
            throw new TokenException(messageSource.getMessage(Constant.Message.TOKEN_EXPIRE, null, Locale.ENGLISH));
        }

        return null;
    }

}
