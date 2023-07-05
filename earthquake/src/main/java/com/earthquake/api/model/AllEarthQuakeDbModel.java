package com.earthquake.api.model;

import com.earthquake.api.entity.AFADEarthquakeEntity;
import com.earthquake.api.entity.KandilliEarthQuakeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Created by Semih, 6.11.2020
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Setter
@NoArgsConstructor
public class AllEarthQuakeDbModel {

    private List<KandilliEarthQuakeEntity> kandilliList;

    private List<AFADEarthquakeEntity> afadList;

}
