package com.crypto.model.coingecko;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Semih, 16.06.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Setter
@Builder
public class CoingeckoApiResponse<T> {

    private T data;
}
