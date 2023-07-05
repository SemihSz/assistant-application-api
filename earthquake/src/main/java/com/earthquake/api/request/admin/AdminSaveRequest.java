package com.earthquake.api.request.admin;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Semih, 23.02.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Setter
public class AdminSaveRequest {

    private String userEmail;

    private String password;

    private String confirmPassword;

    private String firstToken;

    private String secondToken;

}
