package com.earthquake.api.service.filter;

import com.earthquake.api.constant.Constant;
import com.earthquake.api.exception.TokenException;
import com.earthquake.api.model.EarthQuakeInfo;
import com.earthquake.api.request.EarthQuakeDistanceRequest;
import com.earthquake.api.response.EarthQuakeDistanceResponse;
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

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/**
 * Created by Semih, 4.10.2020
 * <p>Find closest earthquake for the user location.</p>
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FindClosestEarthQuakeServiceWithLocation implements SimpleTask<EarthQuakeDistanceRequest, EarthQuakeDistanceResponse> {

    private final CacheService<String, GenericCacheModel<List<EarthQuakeInfo>>> cacheService;

    private final MessageSource messageSource;

    private final UsageCountService usageCountService;

    @Override
    public EarthQuakeDistanceResponse apply(EarthQuakeDistanceRequest request) {

        try {

            final List<EarthQuakeInfo> cacheList = cacheService.get(request.getToken()).getData();
            List<EarthQuakeDistanceResponse> distanceList = new ArrayList<>();

            if (cacheList != null) {
                cacheList.forEach(t -> {
                    final EarthQuakeDistanceResponse response = EarthQuakeDistanceResponse.builder()
                            .id(t.getId())
                            .distance(CalculateDistance.calculateDistance(request.getLatitude(), request.getLongitude(), t.getLatitude(), t.getLongitude()))
                            .quakeLatitude(t.getLatitude())
                            .quakeLongitude(t.getLongitude())
                            .location(t.getLocationName())
                            .magnitude(t.getMagnitude())
                            .build();
                    distanceList.add(response);
                });

                final Comparator<EarthQuakeDistanceResponse> comparator = Comparator.comparing( EarthQuakeDistanceResponse::getDistance );
                usageCountService.accept(Constant.Request.CLOSEST_EARTHQUAKE, ServiceUsageStatusType.SUCCESS);
                return distanceList.stream().min(comparator).get();
            }
            else {
                usageCountService.accept(Constant.Request.CLOSEST_EARTHQUAKE, ServiceUsageStatusType.FAIL);

                throw new TokenException(messageSource.getMessage(null, Locale.ENGLISH));
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            usageCountService.accept(Constant.Request.CLOSEST_EARTHQUAKE, ServiceUsageStatusType.FAIL);
        }

        return null;

    }
}
