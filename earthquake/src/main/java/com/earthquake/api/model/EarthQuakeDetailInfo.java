package com.earthquake.api.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by Semih, 28.09.2020
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Setter
@Builder
public class EarthQuakeDetailInfo {

    private String id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date date;

    private double latitude;

    private double longitude;

    private double magnitude;

    private double depth;

    private String locationName;

    private double distance;

    private String distanceText;
}
