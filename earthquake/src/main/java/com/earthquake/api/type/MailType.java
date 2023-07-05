package com.earthquake.api.type;

import lombok.Getter;

/**
 * Created by Semih, 2.10.2020
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
public enum MailType {

    HIGHEST_MAGNITUDE,
    ALL_FILE_VIA_EXCEL,
    CLOSET_EARTH_QUAKE_LOCATION,
    FILTER_EXCEL_FILE,
    ADMIN_TOKEN,
    ERROR_MAIL
}
