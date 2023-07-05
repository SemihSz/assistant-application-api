package com.earthquake.api.request;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * Created by Semih, 28.09.2020
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Setter
public class EarthQuakeDetailRequest {

    @NonNull
    private String token;

    private Integer limit;

    private double minMagnitude;

    private double maxMagnitude;

    private double depth;

    private double latitude;

    private double longitude;

    private boolean calculateDistance;

}
