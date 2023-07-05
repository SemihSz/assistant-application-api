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
public class EarthquakeDateModel {

    private Map<String, Integer> afad;

    private Map<String, Integer> kandilli;

    private Map<String, Integer> world;

}
