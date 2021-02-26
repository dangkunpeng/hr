package com.magpie.devOps.holiday;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * SlfHolidayServiceImpl
 */
@Slf4j
@Service
public class SlfHolidayServiceImpl implements SlfHolidayService {

	public static final String simple_date_format = "yyyyMMdd";

	public static final SimpleDateFormat sdf = new SimpleDateFormat(simple_date_format);
    @Autowired
    private SlfHolidayRepo slfHolidayRepo;
    @Override
    public List<SlfHoliday> query(String slfYear) {
        log.info("查询{}年的记录", slfYear);
        // 根据年查询相应记录
        List<SlfHoliday> result = this.slfHolidayRepo.findListBySlfYear(slfYear);
        // 空判断
        if (CollectionUtils.isEmpty(result)) {
            // 没有查到记录
            result = initHolidays(slfYear);
            // 保存到数据库
            this.slfHolidayRepo.saveAll(result);
        }
        return result;
    }

    @Override
    public void update(String slfYear, List<String> list) {
        log.info("假日天数{}",list.size());
        // 初始化假期
        this.slfHolidayRepo.resetHolidayByYear(slfYear);
        // 设置有效假期
        this.slfHolidayRepo.setHolidayByYear(list);
    }

    /**
     * 创建默认数据
     */
    public static List<SlfHoliday> initHolidays(String slfYear) {
        List<SlfHoliday> result = new ArrayList<SlfHoliday>();
        // 起始时间
        Calendar calendarBeg = Calendar.getInstance();
        calendarBeg.set(Calendar.YEAR, Integer.parseInt(slfYear));
        calendarBeg.set(Calendar.MONTH, 0);
        calendarBeg.set(Calendar.DATE, 1);
        // 结束时间
        Calendar calendarEnd = Calendar.getInstance();
        calendarEnd.set(Calendar.YEAR, Integer.parseInt(slfYear));
        calendarEnd.set(Calendar.MONTH, 11);
        calendarEnd.set(Calendar.DATE, 31);

        Integer weekCounter = 1;
        while (calendarBeg.getTimeInMillis() <= calendarEnd.getTimeInMillis()) {

            Integer dayOfWeek = calendarBeg.get(Calendar.DAY_OF_WEEK) - 1;

            // 创建记录
            SlfHoliday slfHoliday = new SlfHoliday();

            slfHoliday.setSlfDate(sdf.format(calendarBeg.getTime()));
            slfHoliday.setHoliday(0);
            slfHoliday.setSlfYear(slfYear);
            slfHoliday.setSlfMonth(StringUtils.substring(sdf.format(calendarBeg.getTime()), 4, 6));
            slfHoliday.setSlfWeek(weekCounter);
            slfHoliday.setSlfWeekDay(dayOfWeek);
            result.add(slfHoliday);
            // 向前推进
            calendarBeg.add(Calendar.DATE, 1);
            if (dayOfWeek == Calendar.SUNDAY - 1) {
                weekCounter++;
            }
        }
        log.info("{}年有{}条记录", slfYear, result.size());
        return result;
    }

    
}