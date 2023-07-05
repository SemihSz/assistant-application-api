package com.crypto.model.input;

import lombok.Builder;
import lombok.Getter;

/**
 * Created by Semih, 25.04.2022
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Builder
@Getter
public class FilterAllCoinsInput {

    private String coinName;

    private String coinId;
}
