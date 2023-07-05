package com.crypto.model.coingecko.coin_info;

import lombok.Builder;
import lombok.Getter;

/**
 * Created by Semih, 10.04.2022
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Builder
@Getter
public class AllCoinListInfoModel {

    private String coinId;

    private String coinName;
}
