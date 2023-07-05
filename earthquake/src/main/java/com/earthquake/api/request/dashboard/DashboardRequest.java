package com.earthquake.api.request.dashboard;

import com.earthquake.api.type.InstitutionType;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Semih, 29.05.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Setter
public class DashboardRequest {

    private InstitutionType institutionType;

    private Double magnitude;

    private Double depth;
}
