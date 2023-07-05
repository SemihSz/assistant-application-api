package com.earthquake.api.response.dashboard;

import com.earthquake.api.model.dashboard.EarthquakeDateModel;
import com.earthquake.api.model.dashboard.FilterEarthquakeModel;
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
public class DashboardResponse {

    private Map<String, Long> earthquakeCount;

    private EarthquakeDateModel earthquakeDateModel;

    private FilterEarthquakeModel filterEarthquakeModel;

}
