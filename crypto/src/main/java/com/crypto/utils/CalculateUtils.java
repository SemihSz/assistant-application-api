package com.crypto.utils;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Created by Semih, 19.05.2022
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Component
public class CalculateUtils {


    public static BigDecimal calculatePercentageOfGainOrLoss(BigDecimal currentPrice, BigDecimal purchasePrice) {

        return ((currentPrice.subtract(purchasePrice)).divide(purchasePrice, MathContext.DECIMAL128)).multiply(BigDecimal.valueOf(100)).setScale(2, RoundingMode.CEILING);
    }
}
