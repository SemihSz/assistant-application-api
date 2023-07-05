package com.earthquake.api.model;

import lombok.Builder;
import lombok.Getter;

/**
 * Created by Semih, 11.02.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Builder
public class ConfigModel {

    private String parameterName;

    private String value;

    private String secondValue;
}
