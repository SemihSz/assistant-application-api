package com.earthquake.api.model.world;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * Created by Semih, 21.05.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Builder
public class WorldEarthQuakeQueryModel {

    private List<WorldEarthQuakeQueryList> queryListModel;
}
