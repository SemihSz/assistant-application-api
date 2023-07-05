package com.earthquake.api.model.world;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by Semih, 19.02.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Setter
@Builder
public class WorldEarthQuakeSimpleModel {

    private List<WorldEarthQuakeInformation> earthQuakeList;
}
