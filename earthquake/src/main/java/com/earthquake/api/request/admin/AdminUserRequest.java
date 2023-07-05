package com.earthquake.api.request.admin;

import com.earthquake.api.type.InstitutionType;
import lombok.Getter;
import lombok.Setter;


/**
 * Created by Semih, 12.02.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Setter
public class AdminUserRequest extends AdminBaseRequest {

    private Boolean isSendEmailActive;

    private Boolean isHighestEarthQuakeActive;

    private Boolean isDailyExcelSheetActive;

    private Boolean allDataExcelSheetActive;

    private InstitutionType institutionType;

}
