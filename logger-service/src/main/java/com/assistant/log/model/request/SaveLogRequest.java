package com.assistant.log.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveLogRequest {

    private String IP;

    private String application;

    private String requestBody;

    private String responseBody;

    private String requestDate;

    private String responseDate;

    private Integer status;
}
