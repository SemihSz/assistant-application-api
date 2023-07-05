package com.earthquake.api.shared.util;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * https://www.movable-type.co.uk/scripts/latlong.html?from=64.1265,-21.8174&to=40.7128,-74.0060
 *  Haversine Distance Formula for Java
 */
@Component
public class CalculateDistance {


    public static Double calculateDistance(Double lat1, Double long1, Double lat2, Double long2) {
        final Integer R = 6278137;

        final Double omega1 = lat1 * Math.PI / 180;
        final Double omega2 = lat2 * Math.PI / 180;

        final Double deltaOmega1 = (lat2-lat1) * Math.PI / 180;
        final Double deltaOmega2 = (long2-long1) * Math.PI / 180;

        final Double a = Math.sin(deltaOmega1 / 2) * Math.sin(deltaOmega1 / 2) +
                         Math.cos(omega1) * Math.cos(omega2) * Math.sin(deltaOmega2 / 2) * Math.sin((deltaOmega2 / 2));

        final Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        final Double distanceMeter = R * c;
        return BigDecimal.valueOf(distanceMeter).divide(BigDecimal.valueOf(1000), new MathContext(3, RoundingMode.HALF_DOWN)).doubleValue();
    }


}
