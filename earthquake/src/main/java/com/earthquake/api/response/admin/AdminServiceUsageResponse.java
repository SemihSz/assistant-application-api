package com.earthquake.api.response.admin;

import com.earthquake.api.model.StatusCountModel;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

/**
 * Created by Semih, 17.02.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Builder
public class AdminServiceUsageResponse {

    private Map<String, StatusCountModel> usageResponse;
}
