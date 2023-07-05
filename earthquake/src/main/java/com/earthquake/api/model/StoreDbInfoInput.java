package com.earthquake.api.model;

import com.earthquake.api.type.InstitutionType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Semih, 4.01.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Builder
@Getter
@Setter
public class StoreDbInfoInput {

    private AllEarthQuakeDbModel allEarthQuakeDbModel;

    private InstitutionType institutionType;

    private Integer limit;

    private Double minMagnitude;

    private Double maxMagnitude;

    private Double depth;

    private Double latitude;

    private Double longitude;

    private String location;
}
