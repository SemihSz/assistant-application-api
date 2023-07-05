package com.earthquake.api.request.world;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by Semih, 21.05.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Setter
public class WorldEarthquakeQueryRequest {

    private String earthquakeId;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date date;

    private String location;

    private Double latitude;

    private Double longitude;

    private Double magnitude;

    private Long limit;
}
