package com.earthquake.api.model;

import lombok.Builder;
import lombok.Getter;

/**
 * Created by Semih, 17.02.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Builder
public class StatusCountModel {

    private Long success;

    private Long fail;
}
