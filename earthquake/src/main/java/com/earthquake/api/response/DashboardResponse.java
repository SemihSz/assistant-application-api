package com.earthquake.api.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Semih, 7.02.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Setter
@NoArgsConstructor
public class DashboardResponse {

    private StoreDataDashboardResponse kandilli;

    private StoreDataDashboardResponse afad;
}
