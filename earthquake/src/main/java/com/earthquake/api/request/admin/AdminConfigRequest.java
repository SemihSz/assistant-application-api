package com.earthquake.api.request.admin;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Semih, 20.02.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Setter
public class AdminConfigRequest extends AdminBaseRequest {

    private String parameterName;

    private String value;

    private String secondValue;
}
