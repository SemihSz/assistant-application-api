package com.earthquake.api.request;

import com.earthquake.api.type.InstitutionType;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Semih, 4.01.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Setter
public class StoreDbFilterRequest {

    private InstitutionType institutionType;

    private Integer limit;

    private Double minMagnitude;

    private Double maxMagnitude;

    private Double depth;

    private Double latitude;

    private Double longitude;

    // You have to take information from all-list service, location come from core without any order.
    private String location;
}
