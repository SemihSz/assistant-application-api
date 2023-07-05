package com.earthquake.api.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

/**
 * Created by Semih, 11.10.2020
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Setter
public class DistanceCalculatorRequest {

    @NonNull
    private Double userLatitude;

    @NonNull
    private Double userLongitude;

    @NonNull
    private Double earthquakeLatitude;

    @NonNull
    private Double earthquakeLongitude;
}
