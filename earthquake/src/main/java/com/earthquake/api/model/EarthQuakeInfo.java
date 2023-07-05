package com.earthquake.api.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Builder
public class EarthQuakeInfo implements Serializable {

    private String id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date date;

    private double latitude;

    private double longitude;

    private double magnitude;

    private double depth;

    private String locationName;

}
