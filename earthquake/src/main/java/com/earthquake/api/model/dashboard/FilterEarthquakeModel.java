package com.earthquake.api.model.dashboard;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * Created by Semih, 29.05.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Setter
@Builder
public class FilterEarthquakeModel {

    private Map<String, Integer> dailyAfad;

    private Map<String, Integer> dailyKandilli;

    private Map<String, Integer> dailyWorld;

    private Map<String, Integer> magnitudeYearAfad;

    private Map<String, Integer> magnitudeYearKandilli;

    private Map<String, Integer> magnitudeYearWorld;

    private Map<String, Integer> magnitudeMonthAfad;

    private Map<String, Integer> magnitudeMonthKandilli;

    private Map<String, Integer> magnitudeMonthWorld;

    private Map<String, Integer> magnitudeDailyAfad;

    private Map<String, Integer> magnitudeDailyKandilli;

    private Map<String, Integer> magnitudeDailyWorld;

    private Map<String, Integer> depthYearAfad;

    private Map<String, Integer> depthYearKandilli;

    private Map<String, Integer> depthYearWorld;

    private Map<String, Integer> depthMonthAfad;

    private Map<String, Integer> depthMonthKandilli;

    private Map<String, Integer> depthMonthWorld;

    private Map<String, Integer> depthDailyKandilli;

    private Map<String, Integer> depthDailyWorld;

    private Map<String, Integer> depthDailyAfad;


}
