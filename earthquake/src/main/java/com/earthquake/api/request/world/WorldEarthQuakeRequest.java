package com.earthquake.api.request.world;

import com.earthquake.api.type.InstitutionType;
import com.earthquake.api.type.SearchType;
import com.earthquake.api.type.WorldEarthQuakeRequestType;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Semih, 18.02.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Setter
public class WorldEarthQuakeRequest {

    private String orderBy;

    private String limit;

    private Double minMagnitude;

    private WorldEarthQuakeRequestType requestType;

    private SearchType searchType;

    private String token;

    private InstitutionType institutionType;

}
