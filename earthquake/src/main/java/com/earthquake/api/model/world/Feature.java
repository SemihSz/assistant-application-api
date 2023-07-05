package com.earthquake.api.model.world;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Semih, 18.02.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Setter
public class Feature {

    private String type;

    private Properties properties;

    private Geometry geometry;

    private String id;
}
