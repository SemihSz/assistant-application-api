package com.earthquake.api.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Semih, 11.10.2020
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Setter
@Builder
public class DistanceUserLocationResponse {

    private Double distance;

    private Double userLatitude;

    private Double userLongitude;

    private Double earthquakeLatitude;

    private Double earthquakeLongitude;

}
