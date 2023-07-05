package com.crypto.model.coingecko.coin_info;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by Semih, 9.04.2022
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Setter
@Builder
public class CoinInfoModel {

    private String coinId;

    private String coinName;

    private List<CoinInfoPrice> priceList;

}
