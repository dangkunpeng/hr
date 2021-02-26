package com.magpie.devOps.holiday;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.magpie.devOps.utils.PageUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * SlfHolidayController
 */
@Slf4j
@RestController
@RequestMapping(value = "/holiday", method = {RequestMethod.POST, RequestMethod.GET})
public class SlfHolidayController {

    @Autowired
    private SlfHolidayService slfHolidayService;

    @Autowired
    private SlfHolidayRepo slfHolidayRepo;

    /**
     * @param list
     * @return
     */
    public static List<SlfHolidayView> formatWeek(List<SlfHoliday> list) {

        List<SlfHolidayView> result = new ArrayList<SlfHolidayView>();

        Map<String, List<SlfHoliday>> map = list.stream().collect(Collectors.groupingBy(slfHoliday -> groupBy(slfHoliday)));

        for (List<SlfHoliday> group : map.values()) {
//        	log.info("当前分组为{}", groupBy(group.get(0)));
            SlfHolidayView view = new SlfHolidayView();
            view.setSlfWeek(group.get(0).getSlfWeek());
            view.setSlfMonth(group.get(0).getSlfMonth());
            view.setSlfYear(group.get(0).getSlfYear());
            for (SlfHoliday slfHoliday : group) {
                String slfDate = slfHoliday.getSlfDate();
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, Integer.parseInt(StringUtils.substring(slfDate, 0, 4)));
                calendar.set(Calendar.MONTH, Integer.parseInt(StringUtils.substring(slfDate, 4, 6)) - 1);
                calendar.set(Calendar.DATE, Integer.parseInt(StringUtils.substring(slfDate, 6, 8)));

                switch (slfHoliday.getSlfWeekDay() + 1) {
                    case Calendar.SUNDAY:
                        view.setSunday(slfDate);
                        break;
                    case Calendar.MONDAY:
                        view.setMonday(slfDate);
                        break;
                    case Calendar.TUESDAY:
                        view.setTuesday(slfDate);
                        break;
                    case Calendar.WEDNESDAY:
                        view.setWednesday(slfDate);
                        break;
                    case Calendar.THURSDAY:
                        view.setThursday(slfDate);
                        break;
                    case Calendar.FRIDAY:
                        view.setFriday(slfDate);
                        break;
                    case Calendar.SATURDAY:
                        view.setSaturday(slfDate);
                        break;

                    default:
                        break;
                }
            }
            result.add(view);
        }
        result.sort((a1, a2) -> groupBy(a1).compareTo(groupBy(a2)));
        return result;
    }

    public static String groupBy(SlfHoliday slfHoliday) {
        StringBuilder result = new StringBuilder();
        result.append("-");
        result.append(slfHoliday.getSlfMonth());
        result.append("-");
        if (slfHoliday.getSlfWeek() < 10) {
            result.append("0");
        }
        result.append(slfHoliday.getSlfWeek());
        return result.toString();
    }

    @RequestMapping(value = "/query/{slfYear}")
    public String query(@PathVariable String slfYear) {
        log.info("query");
        List<SlfHoliday> list = this.slfHolidayService.query(slfYear);
        List<SlfHolidayView> result = formatWeek(list);
        return JSONObject.toJSONString(result);
    }

    @RequestMapping(value = "/update")
    public String update(@RequestBody SlfHoliday slfHoliday) {
        log.info("query");
        String[] array = StringUtils.split(slfHoliday.getSlfDate(), ",");

        List<String> list = new ArrayList<String>();
        for (String slfDate : array) {
            if (StringUtils.isNotBlank(slfDate)) {
                list.add(slfDate);
            }
        }
        this.slfHolidayService.update(slfHoliday.getSlfYear(), list);
        ;
        return "success";
    }

    @RequestMapping(value = "/query/page")
    public String queryPage(@RequestBody SlfHolidayForm param) {
        log.info("query holiday by Page");
        // 分页信息
        Pageable pageable = PageUtils.newPageQuery(param);
        // 查询条件
        SlfHoliday slfHoliday = new SlfHoliday();
        BeanUtils.copyProperties(param, slfHoliday);
        Example<SlfHoliday> example = Example.of(slfHoliday);
        // 调用
        Page<SlfHoliday> page = this.slfHolidayRepo.findAll(example, pageable);
        return PageUtils.newPage(page);
    }
}