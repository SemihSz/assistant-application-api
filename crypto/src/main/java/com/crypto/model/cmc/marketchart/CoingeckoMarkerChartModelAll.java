package com.crypto.model.cmc.marketchart;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by Semih, 19.09.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Builder
public class CoingeckoMarkerChartModelAll {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalDateTime time;

    private BigDecimal price;

    private BigDecimal marketCap;

    private BigDecimal volume;
}
