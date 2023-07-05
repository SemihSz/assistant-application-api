package com.earthquake.api.service.dashboard;

import com.earthquake.api.request.dashboard.DashboardRequest;
import com.earthquake.api.response.dashboard.DashboardResponse;
import org.springframework.stereotype.Service;

/**
 * Created by Semih, 29.05.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Service
public interface GeneralDashboardService {

    DashboardResponse getEarthquakeDashboard(DashboardRequest request);
}
