package com.magpie.devOps.thyme;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.magpie.devOps.holiday.SlfHoliday;
import com.magpie.devOps.holiday.SlfHolidayRepo;
import com.magpie.devOps.ops.Magpie;
import com.magpie.devOps.ops.MagpieRepo;
import com.magpie.devOps.utils.Utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(method = RequestMethod.GET)
public class ThymeLeafController {

    @Autowired
    private MagpieRepo magpieRepo;
    @Autowired
    private SlfHolidayRepo slfHolidayRepo;
    /**
     * 映射地址是：/templates/hello
     * 
     * @return
     */
    @RequestMapping("/")
    public String main(Map<String, Object> map) {
        List<Magpie> list = this.magpieRepo.findAll();
        map.put("name", "Andy");
        map.put("list", list);
        map.put("thisDate",Utils.getDay());
        return "biz/magpie";
    }
    /**
     * 映射地址是：/templates/hello
     * 
     * @return
     */
    @RequestMapping("/holiday")
    public String holiday(Map<String, Object> map) {
    	String year = "";
    	List<String> day = this.slfHolidayRepo.findMaxSlfYear();
    	if (CollectionUtils.isEmpty(day)) {
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
    		year = sdf.format(new Date());
    	} else {
    		year = day.get(0);
    	}
    	map.put("year", year);
    	log.info("最大年份是{}", year);
    	List<SlfHoliday> listHoliday = this.slfHolidayRepo.findListBySlfYearAndHoliday(year, 1);
    	StringBuilder selectedValue = new StringBuilder();
    	for (SlfHoliday slfHoliday : listHoliday) {
    		selectedValue.append(slfHoliday.getSlfDate());
    		selectedValue.append(",");
		}
    	map.put("selectedValue", selectedValue);
    	
    	
        return "biz/holiday";
    }
    
    /**
     * 映射地址是：/templates/menu
     * 
     * @return
     */
    @RequestMapping("/page")
    public String jump(Map<String, Object> map, HttpServletRequest request) {
    	map.put("thisDate",Utils.getDay());
    	
    	String page = request.getParameter("id");
    	map.put("activePage",page);
    	log.info("跳转到页面{}", page);
        return "biz/" + page;
    }
}