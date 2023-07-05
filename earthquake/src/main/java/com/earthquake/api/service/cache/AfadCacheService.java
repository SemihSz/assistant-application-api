package com.earthquake.api.service.cache;

import com.earthquake.api.constant.Constant;
import com.earthquake.api.exception.TokenException;
import com.earthquake.api.model.EarthQuakeInfo;
import com.earthquake.api.request.EarthQuakeRequest;
import com.earthquake.api.response.EarthQuakeResponse;
import com.earthquake.api.service.cache.model.GenericCacheModel;
import com.earthquake.api.service.core.CoreAfadService;
import com.earthquake.api.service.core.CoreAfadV2Service;
import com.earthquake.api.service.filter.AfadAllEarthQuakeListService;
import com.earthquake.api.service.token.TokenService;
import com.earthquake.api.service.usage.UsageCountService;
import com.assistant.commonapi.task.SimpleTask;
import com.earthquake.api.type.InstitutionType;
import com.earthquake.api.type.ServiceUsageStatusType;
import lombok.RequiredArgsConstructor;
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
@Slf4j
@RequiredArgsConstructor
public class AfadCacheService implements SimpleTask<EarthQuakeRequest, EarthQuakeResponse> {

    private final CoreAfadService coreAfadService;

    private final CoreAfadV2Service coreAfadV2Service;

    private final TokenService tokenService;

    private final AfadAllEarthQuakeListService afadAllEarthQuakeListService;

    private final CacheService<String, GenericCacheModel<List<EarthQuakeInfo>>> cacheService;

    private final MessageSource messageSource;

    private final UsageCountService usageCountService;

    @Override
    public EarthQuakeResponse apply(EarthQuakeRequest request) {

        EarthQuakeResponse response = new EarthQuakeResponse();
        try {

            if (Objects.nonNull(request.getToken())) {

                if (InstitutionType.AFAD.equals(request.institutionType)) {

                    if (tokenService.controlTokenActive(request.getToken())) {
                        final GenericCacheModel<List<EarthQuakeInfo>> getCacheInfo = cacheService.get(request.getToken());

                        if (Objects.nonNull(getCacheInfo) && InstitutionType.AFAD.equals(getCacheInfo.getInstitutionType())) {
                            response.setAfadQuakeList(getCacheInfo.getData());
                        }
                        else {

                            final List<EarthQuakeInfo> afadList = afadAllEarthQuakeListService.apply(coreAfadV2Service.coreAfadService()).getAfadQuakeList();
                            response.setAfadQuakeList( afadList);
                            GenericCacheModel<List<EarthQuakeInfo>> cacheModel = new GenericCacheModel<>();
                            cacheModel.setInstitutionType(request.institutionType);
                            cacheModel.setData(afadList);
                            log.info("Start afad info to cache");
                            cacheService.put(request.getToken(), cacheModel);
                            log.info("Finish afad info to cache");
                            response.setAfadQuakeList(afadList);

                        }
                    }
                    else {
                        throw new TokenException(messageSource.getMessage(Constant.Message.TOKEN_EXPIRE, null, Locale.ENGLISH));
                    }

                }
                else {
                    log.error("Institution type must be AFAD");
                }
            }

        }
        catch (Exception e) {
            usageCountService.accept(Constant.Request.ALL_LIST, ServiceUsageStatusType.FAIL);
            log.error("Error afad info to cache");
            throw new TokenException(messageSource.getMessage(Constant.Message.TOKEN_EXPIRE, null, Locale.ENGLISH));
        }

        usageCountService.accept(Constant.Request.ALL_LIST, ServiceUsageStatusType.SUCCESS);

        return response;
    }
}
