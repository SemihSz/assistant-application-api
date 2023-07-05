package com.earthquake.api.model.world;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by Semih, 18.02.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Setter
public class Geometry {

    private String type;

    private List<Double> coordinates;
}
