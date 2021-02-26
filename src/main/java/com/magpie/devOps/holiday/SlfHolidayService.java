package com.magpie.devOps.holiday;

import java.util.List;

/**
 * SlfHolidayService
 */
public interface SlfHolidayService {

    /**
     * 查询，如果没有数据就初始化
     * @param slfYear
     */
    public List<SlfHoliday> query(String slfYear);
    /**
     * 更新数据
     * @param list
     */
    public void update(String slfYear, List<String> list);
}