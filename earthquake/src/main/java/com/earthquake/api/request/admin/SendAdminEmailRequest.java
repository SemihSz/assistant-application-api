package com.earthquake.api.request.admin;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Semih, 24.02.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Setter
@Builder
public class SendAdminEmailRequest {

    private String email;
}
