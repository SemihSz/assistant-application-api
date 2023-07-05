package com.earthquake.api.request;

import com.earthquake.api.type.InstitutionType;
import com.earthquake.api.type.SaveEmailType;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Semih, 21.11.2020
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Setter
public class SaveEmailRequest {

    private String email;

    private String newEmail;

    private SaveEmailType emailType;

    private Boolean isSendEmailActive;

    private Boolean isHighestEarthQuakeActive;

    private Boolean isDailyExcelSheetActive;

    private Boolean allDataExcelSheetActive;

    private InstitutionType institutionType;

}
