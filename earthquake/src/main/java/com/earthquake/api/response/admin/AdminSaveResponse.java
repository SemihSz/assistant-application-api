package com.earthquake.api.response.admin;

import com.earthquake.api.type.AdminSaveStatusType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Semih, 23.02.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Setter
@Builder
public class AdminSaveResponse {

    private AdminSaveStatusType statusType;
}
