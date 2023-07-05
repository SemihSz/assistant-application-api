package com.earthquake.api.response;

import com.earthquake.api.entity.KandilliEarthQuakeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * Created by Semih, 6.02.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Setter
@NoArgsConstructor
public class StoreDataDashboardResponse {

    private Map<String, List<KandilliEarthQuakeEntity>> earthQuakeLocationCount;

    private Map<String, Integer> countLocation;

    // x > 4.5
    private Integer higherFromFiveMagnitudeCount;

    // x < 4.5
    private Integer lowerFromFiveMagnitudeCount;

    // x > 10
    private Integer depthHigherTenKm;

    // x < 10
    private Integer depthLowerTenKm;


}
