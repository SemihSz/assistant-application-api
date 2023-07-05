package com.crypto.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by Semih, 16.06.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@ToString(doNotUseGetters = true)
@EqualsAndHashCode(callSuper = false)
public class RestRequestHeader implements Serializable {

    private String apiKey;

    public RestRequestHeader() {
    }

    public RestRequestHeader(String apiKey) {
        this.apiKey = apiKey;
    }

    public void setRequestHeader(RestRequestHeader requestHeader) {
        this.apiKey = requestHeader.apiKey;
    }
}
