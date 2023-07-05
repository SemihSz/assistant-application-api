package com.earthquake.api.shared.util;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by Semih, 5.02.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Component
@NoArgsConstructor
public class DateUtils {


    public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public static Date convertTimeStampToDate(String timeStamp) {
        Timestamp ts = new Timestamp(Long.parseLong(timeStamp));
        return new Date(ts.getTime());
    }
}
