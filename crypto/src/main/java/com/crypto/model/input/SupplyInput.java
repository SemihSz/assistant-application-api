package com.crypto.model.input;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by Semih, 15.06.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Setter
@Builder
public class SupplyInput {

    private BigDecimal maxSupply;

    private BigDecimal circulatingSupply;

    private BigDecimal totalSupply;
}
