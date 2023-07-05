package com.earthquake.api.service.filter;

import com.earthquake.api.constant.Constant;
import com.earthquake.api.exception.TokenException;
import com.earthquake.api.model.EarthQuakeDetailInfo;
import com.earthquake.api.model.EarthQuakeInfo;
import com.earthquake.api.request.EarthQuakeDetailRequest;
import com.earthquake.api.response.GenericResponse;
import com.earthquake.api.service.cache.CacheService;
import com.earthquake.api.service.cache.model.GenericCacheModel;
import com.earthquake.api.service.usage.UsageCountService;
import com.earthquake.api.shared.util.CalculateDistance;
import com.assistant.commonapi.task.SimpleTask;
import com.earthquake.api.type.ServiceUsageStatusType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Semih, 28.09.2020
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DetailFilterSearchService implements SimpleTask<EarthQuakeDetailRequest, GenericResponse<?>> {

    private final CacheService<String, GenericCacheModel<List<EarthQuakeInfo>>> cacheService;

    private final MessageSource messageSource;

    private final UsageCountService usageCountService;

    @Override
    public GenericResponse<List<EarthQuakeDetailInfo>> apply(EarthQuakeDetailRequest request) {

        try {

            final List<EarthQuakeInfo> cacheList = cacheService.get(request.getToken()).getData();
            List<EarthQuakeDetailInfo> detailInfoList = new ArrayList<>();
            List<EarthQuakeDetailInfo> sortedList = new ArrayList<>();
            if (cacheList != null) {
                cacheList.stream()
                        .filter(t -> request.getMinMagnitude() < t.getMagnitude())
                        .filter(t -> request.getDepth() < t.getDepth())
                        .limit((Objects.nonNull(request.getLimit()) && request.getLimit() != 0) ? request.getLimit() : 0)
                        .forEach(t -> {
                            final double distanceBetweenYourLocation = request.isCalculateDistance() ? CalculateDistance.calculateDistance(request.getLatitude(), request.getLongitude(),
                                    t.getLatitude(), t.getLongitude()) : 0;
                            final EarthQuakeDetailInfo info = EarthQuakeDetailInfo.builder()
                                    .date(t.getDate())
                                    .id(t.getId())
                                    .depth(t.getDepth())
                                    .magnitude(t.getMagnitude())
                                    .locationName(t.getLocationName())
                                    .latitude(t.getLatitude())
                                    .longitude(t.getLongitude())
                                    /**
                                     * if is calculate distance active, this service provide calculate of filter number earthquakes.
                                     * */
                                    .distance(distanceBetweenYourLocation)
                                    .distanceText(String.valueOf(distanceBetweenYourLocation).concat(" km")).build();
                            detailInfoList.add(info);
                        });

                GenericResponse<List<EarthQuakeDetailInfo>> response = new GenericResponse<>();

                if (request.isCalculateDistance()) {
                    sortedList = detailInfoList.stream().sorted(Comparator.comparing(EarthQuakeDetailInfo::getDistance).reversed()).collect(Collectors.toList());
                    response.setResponse(sortedList);
                }

                response.setResponse(detailInfoList);

                usageCountService.accept(Constant.Request.DETAIL_FILTER, ServiceUsageStatusType.SUCCESS);

                return response;
            }

            else {
                usageCountService.accept(Constant.Request.DETAIL_FILTER, ServiceUsageStatusType.SUCCESS);
                throw new TokenException(messageSource.getMessage(Constant.Message.TOKEN_EXPIRE, null, Locale.ENGLISH));
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            usageCountService.accept(Constant.Request.DETAIL_FILTER, ServiceUsageStatusType.SUCCESS);
        }

        return null;
    }
}
