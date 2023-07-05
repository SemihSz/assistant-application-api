package com.earthquake.api.model.dashboard;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by Semih, 30.05.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Setter
@Builder
public class DashboardFilterInput {

    private List<DashboardModel> afad;

    private List<DashboardModel> kandilli;

    private List<DashboardModel> world;

    private Double magnitude;

    private Double depth;
}
