package com.earthquake.api.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Semih, 4.10.2020
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Setter
@Builder
public class ExcelFilterInput {

    private Integer limit;

    private double minMagnitude;

    private double maxMagnitude;

    private double depth;

    private double latitude;

    private double longitude;
}
