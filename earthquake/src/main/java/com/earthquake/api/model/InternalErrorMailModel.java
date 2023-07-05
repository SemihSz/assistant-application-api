package com.earthquake.api.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Semih, 29.09.2020
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Setter
@NoArgsConstructor
public class InternalErrorMailModel<T> {

    private String emailAddress;

    private T exception;

    private boolean sendErrorEmailActive;
}
