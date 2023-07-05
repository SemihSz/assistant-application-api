package com.earthquake.api.model.dashboard;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Semih, 30.05.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Setter
@Builder
public class EarthquakeMonthModel {

    private String month;

    private Integer monthNumber;

    private Long earthquakeCount;
}
