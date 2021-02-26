package com.magpie.devOps.holiday;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.magpie.devOps.common.EntityParam;
import lombok.Data;

/**
 * SlfHoliday
 */
@Data
@Entity
@Table
public class SlfHoliday extends EntityParam  {

    @Id
    private String slfDate;

    @Column
    private String slfYear;
    
    @Column
    private String slfMonth;

    @Column
    private Integer slfWeek;

    @Column
    private Integer slfWeekDay;

    @Column
    private Integer holiday;
}