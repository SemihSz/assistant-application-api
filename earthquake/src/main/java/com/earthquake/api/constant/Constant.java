package com.earthquake.api.constant;

import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class Constant {

    public static final int SUCCESS_CODE = 200;

    public static final int FAIL_CODE = 500;

    public static final int TOKEN_CODE = 499;

    public static final String USER_AGENT = "Mozilla/5.0";

    public static final String DATE_FORMAT_ONSOURCE = "yyyy.MM.dd HH:mm:ss";

    public static final String AFAD_DATE_FORMAT = "dd.MM.yyyy HH:mm:SS";

    public static final int TOKEN_SIZE = 32;

    public static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!?&_+^%(){}][";

    public static final String EXCEL_SUCCESS = "Excel file generate successfully.";

    public static final String EXCEL_FAIL = "Excel file generate fail.";

    public static final Locale EN_LOCALE = Locale.ENGLISH;

    public static class Message {

        public static final String ERROR_MAIL_BODY = "core.email.body";

        public static final String TOKEN_EXPIRE = "token.expire";

        public static final String ADMIN_TOKEN_EXPIRE = "admin.token.expire";

        public static final String HIGHEST_MAGNITUDE_EMAIL_BODY = "email.body.highest.magnitude";

        public static final String HIGHEST_MAGNITUDE_EMAIL_SUBJECT = "email.subject.highest.magnitude";

        public static final String EXCEL_EMAIL_BODY = "email.body.excel";

        public static final String EXCEL_EMAIL_SUBJECT = "email.subject.excel";

        public static final String EXCEL_FILTER_BODY = "email.body.general.excel";

        public static final String EXCEL_FILTER_SUBJECT = "email.subject.excel.filter";

        public static final String CLOSEST_EARTH_QUAKE_SUBJECT = "email.subject.closest";

        public static final String CLOSEST_EARTH_QUAKE_BODY = "email.body.closest";

        public static final String CORE_ERROR_MAIL_SUBJECT = "core.error.mail.subject";

        public static final String CORE_ERROR_MAIL_BODY = "core.error.mail.body";

        public static final String SAVE_EMAIL_SUCCESS = "save.email.success";

        public static final String SAVE_EMAIL_FAIL = "save.email.fail";

        public static final String ADMIN_TOKEN = "admin.token";

        public static final String ADMIN_TOKEN_BODY = "admin.token.body";
    }

    public static class Excel {

        public static final String GENERAL_EXCEL_FILE_NAME = "earth-quake.xlsx";

        public static final String FILTER_EXCEL_FILE_NAME = "filter-earth-quake.xlsx";
    }

    public static class Request {

        public static final String TOKEN = "token";

        public static final String ALL_LIST = "all-list";

        public static final String FIRST_N_LIST = "first-n-list";

        public static final String CALCULATE_DISTANCE = "calculate/distance";

        public static final String FILTER_MAGNITUDE_LIST = "filter-magnitude-list";

        public static final String FILTER_DEPTH_LIST = "filter-depth-list";

        public static final String POWER_MAGNITUDE = "power-magnitude";

        public static final String DISTANCE_EARTHQUAKE = "distance-earthquake";

        public static final String EXCEL_LIST = "excel-list";

        public static final String DETAIL_FILTER = "detail-filter";

        public static final String SEND_EMAIL = "send-email";

        public static final String CLOSEST_EARTHQUAKE = "closest-earthquake";

        public static final String EXCEL_EARTHQUAKE = "excel/earthquake";

        public static final String STORE_ALL_EARTH_QUAKE_LIST = "storeAllEarthquakeList";

        public static final String EXCEL_STORE_DATA_EARTHQUAKE_INFO = "excel/store-data-earthquake-info";

        public static final String SAVE_EMAIL = "save-email";

        public static final String STORE_DB_FILTER = "storeDb-filter";

        public static final String DASHBOARD = "dashboard";

        public static final String ADMIN_EMAIL_STATUS_REQUEST = "email-status";

        public static final String ADMIN_SHOW_USERS = "users";

        public static final String ADMIN_FILTER_USERS = "filter-users";

        public static final String WORLD_EARTH_QUAKE_RESULT = "world-earthquake-result";

        public static final String WORLD_EARTH_QUAKE_QUERY = "world-earthquake-query";

    }
}
