package com.earthquake.api.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Created by Semih, 28.09.2020
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Setter
public class ExceptionResponse {

    private String errorMessage;

    private int errorCode;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

}
