package com.earthquake.api.request.admin;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Semih, 12.02.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Setter
public class AdminBaseRequest {

    private String adminToken;

    private String secondToken;

    private String confirmationToken;

    private String userEmail;
}
