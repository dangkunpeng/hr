package com.magpie.devOps.holiday;

import com.magpie.devOps.common.QueryParam;
import lombok.Data;

@Data
public class SlfHolidayForm extends QueryParam {
    private String slfDate;
    private String slfYear;
    private String slfMonth;
    private Integer slfWeek;
    private Integer slfWeekDay;
    private Integer holiday;
}
