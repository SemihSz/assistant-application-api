package com.crypto.request.coingecko;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Semih, 11.04.2022
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Setter
@Builder
public class CoreCompaniesTreasuryRequest {

    private String coinId;
}
