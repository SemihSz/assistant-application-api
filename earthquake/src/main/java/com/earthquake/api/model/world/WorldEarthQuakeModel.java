package com.earthquake.api.model.world;

import lombok.Builder;
import lombok.Getter;

/**
 * Created by Semih, 18.02.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Builder
public class WorldEarthQuakeModel {

    private Root detailModel;

    private WorldEarthQuakeSimpleModel simpleModel;
}
