package com.earthquake.api.model.world;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

/**
 * Created by Semih, 19.02.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Builder
public class WorldEarthQuakeInformation {


    private Geometry geometry;

    private String id;

    private double mag;

    private String place;

    private Date time;

    private Object updated;

}
