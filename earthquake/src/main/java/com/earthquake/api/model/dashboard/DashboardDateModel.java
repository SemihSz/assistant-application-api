package com.earthquake.api.model.dashboard;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Created by Semih, 30.05.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Setter
@Builder
public class DashboardDateModel {

    private LocalDate date;

    private int year;

    private int month;

    private int day;

}
