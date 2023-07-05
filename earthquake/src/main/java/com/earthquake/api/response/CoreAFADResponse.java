package com.earthquake.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by Semih, 4.11.2020
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CoreAFADResponse implements Serializable {

    @JsonProperty("time")
    private String time;

    @JsonProperty("lat")
    private String latitude;

    @JsonProperty("lon")
    private String longitude;

    @JsonProperty("depth")
    private String depth;

    @JsonProperty("type")
    private String type;

    @JsonProperty("m")
    private String magnitude;

    @JsonProperty("country")
    private String country;

    @JsonProperty("city")
    private String city;

    @JsonProperty("district")
    private String district;

    @JsonProperty("town")
    private String town;

    @JsonProperty("other")
    private String other;

    @JsonProperty("eventId")
    private String eventId;

    @JsonProperty("agency")
    private String agency;

    @JsonProperty("rms")
    private String rms;

}
