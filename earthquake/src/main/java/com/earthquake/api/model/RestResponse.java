package com.earthquake.api.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Data
@Getter
@Setter
public class RestResponse<T> implements Serializable {

    private static final long serialVersionId = 1L;

    private Long timestamp;

    private int responseCode;

    private T response;

    public RestResponse(int responseCode, T response) {
        this.timestamp = new Date().getTime();
        this.responseCode = responseCode;
        this.response = response;
    }

    public RestResponse(int responseCode, Exception e) {
        this.timestamp = new Date().getTime();
        this.responseCode = responseCode;
    }

    public RestResponse(int responseCode) {
        this.timestamp = new Date().getTime();
        this.responseCode = responseCode;
    }
}
