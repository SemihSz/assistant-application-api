package com.earthquake.api.entity;

import com.earthquake.api.type.InstitutionType;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

/**
 * Created by Semih, 17.11.2020
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {

    @Id
    private String userId;

    private String email;

    private Boolean isSendEmailActive;

    private Boolean isHighestEarthQuakeActive;

    private Boolean isDailyExcelSheetActive;

    private Boolean allDataExcelSheetActive;

    @Enumerated(EnumType.STRING)
    private InstitutionType institutionType;


}
