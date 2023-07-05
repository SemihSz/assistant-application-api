package com.earthquake.api.request;

import com.earthquake.api.type.InstitutionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EarthQuakeRequest {

    @NonNull
    private String token;

    private Integer limit;

    private double minMagnitude;

    private double maxMagnitude;

    private double depth;

    public InstitutionType institutionType;
}
