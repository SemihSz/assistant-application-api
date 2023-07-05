package com.earthquake.api.service.distance;

import com.earthquake.api.constant.Constant;
import com.earthquake.api.request.DistanceCalculatorRequest;
import com.earthquake.api.response.DistanceUserLocationResponse;
import com.earthquake.api.service.usage.UsageCountService;
import com.earthquake.api.shared.util.CalculateDistance;
import com.assistant.commonapi.task.SimpleTask;
import com.earthquake.api.type.ServiceUsageStatusType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Created by Semih, 11.10.2020
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DistanceCalculatorWithUserLocationService implements SimpleTask<DistanceCalculatorRequest, DistanceUserLocationResponse> {

    private final UsageCountService usageCountService;

    @Override
    public DistanceUserLocationResponse apply(DistanceCalculatorRequest request) {

        if (Objects.nonNull(request.getUserLatitude()) && Objects.nonNull(request.getUserLongitude())
                && Objects.nonNull(request.getEarthquakeLatitude()) && Objects.nonNull(request.getEarthquakeLongitude())) {
            usageCountService.accept(Constant.Request.DISTANCE_EARTHQUAKE, ServiceUsageStatusType.SUCCESS);
            return DistanceUserLocationResponse.builder()
                    .distance(CalculateDistance.calculateDistance(request.getUserLatitude(), request.getUserLongitude(),
                            request.getEarthquakeLatitude(), request.getEarthquakeLongitude()))
                    .userLatitude(request.getUserLatitude())
                    .userLongitude(request.getUserLongitude())
                    .earthquakeLatitude(request.getEarthquakeLatitude())
                    .earthquakeLongitude(request.getEarthquakeLongitude())
                    .build();
        }
        else {
            usageCountService.accept(Constant.Request.DISTANCE_EARTHQUAKE, ServiceUsageStatusType.FAIL);
            throw new NullPointerException("This user coordinates and earthquake coordinates cannot be null!");
        }
    }
}
