package com.earthquake.api.request;

import com.earthquake.api.type.InstitutionType;
import com.earthquake.api.type.MailType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

/**
 * Created by Semih, 29.09.2020
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Setter
public class SendEarthQuakeEmailRequest {

    private String email;

    private String token;

    private MailType mailType;

    private String id;

    @NonNull
    private Double latitude;

    @NonNull
    private Double longitude;

    private Integer limit;

    private double minMagnitude;

    private double maxMagnitude;

    private double depth;

    private InstitutionType institutionType;

}
