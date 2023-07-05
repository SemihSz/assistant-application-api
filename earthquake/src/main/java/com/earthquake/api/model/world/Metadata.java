package com.earthquake.api.model.world;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Semih, 18.02.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Setter
public class Metadata {

    private long generated;

    private String url;

    private String title;

    private int status;

    private String api;

    private int limit;

    private int offset;

    private int count;
}
