package com.earthquake.api.service.filter;


import com.assistant.commonapi.task.SimpleTask;
import com.earthquake.api.model.EarthQuakeInfo;
import com.earthquake.api.request.EarthQuakeRequest;
import com.earthquake.api.response.EarthQuakeResponse;
import com.earthquake.api.service.cache.CacheService;
import com.earthquake.api.service.cache.model.GenericCacheModel;
import com.earthquake.api.type.InstitutionType;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetKandilliInfoAllService implements SimpleTask<EarthQuakeRequest, EarthQuakeResponse> {

    private final AllEarthQuakeListService allEarthQuakeListService;

    private final CacheService<String, GenericCacheModel<List<EarthQuakeInfo>>> cacheService;

    @SneakyThrows
    @Override
    public EarthQuakeResponse apply(EarthQuakeRequest request) {

        final GenericCacheModel cacheList = cacheService.get(request.getToken());

        if (Objects.nonNull(cacheList) && InstitutionType.KANDILLI.equals(request.institutionType) && InstitutionType.KANDILLI.equals(cacheList.getInstitutionType())) {
            return EarthQuakeResponse.builder().quakeInfoList((List<EarthQuakeInfo>) cacheList.getData()).build();
        }

        return EarthQuakeResponse.builder().quakeInfoList(allEarthQuakeListService.getAllEarthQuakes()).build();
    }
}
