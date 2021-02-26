package com.magpie.devOps.holiday;

import lombok.Data;

/**
 * SlfHolidayView
 */
@Data
public class SlfHolidayView extends SlfHoliday {

    private String monday;
    private String tuesday;
    private String wednesday;
    private String thursday;
    private String friday;
    private String saturday ;
    private String sunday;
}