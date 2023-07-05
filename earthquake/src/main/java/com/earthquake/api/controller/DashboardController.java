package com.earthquake.api.controller;

import com.earthquake.api.model.RestResponse;
import com.earthquake.api.request.dashboard.DashboardRequest;
import com.earthquake.api.response.dashboard.DashboardResponse;
import com.earthquake.api.service.dashboard.GeneralDashboardService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.earthquake.api.constant.Constant.SUCCESS_CODE;

/**
 * Created by Semih, 29.05.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@RestController
@RequestMapping("/com/earthquake/api/dashboard")
@AllArgsConstructor
public class DashboardController {

    private final GeneralDashboardService generalDashboardService;

    @PostMapping(value = "/get-earthquake", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<RestResponse<DashboardResponse>> getEarthquakeDashboard(DashboardRequest request) {

        return ResponseEntity.ok(new RestResponse<DashboardResponse>(SUCCESS_CODE, generalDashboardService.getEarthquakeDashboard(request)));
    }

}
