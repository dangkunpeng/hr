package com.magpie.devOps.hr;

import com.google.common.collect.Lists;
import com.magpie.devOps.hr.entity.HrPeople;
import com.magpie.devOps.hr.svc.HrPeopleSvc;
import com.magpie.devOps.utils.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping(method = RequestMethod.POST, value = "hr/people")
public class HrPeopleControllor {
    @Resource
    private HrPeopleSvc hrPeopleSvc;

    @ResponseBody
    @RequestMapping("/list")
    public Result queryAll(@RequestBody HrPeople hrPeople) {
        return this.hrPeopleSvc.queryALl(hrPeople);
    }

    @ResponseBody
    @RequestMapping("/save")
    public Result save(@RequestBody HrPeople hrPeople) {
        return this.hrPeopleSvc.saveOrUpdate(hrPeople);
    }

    @ResponseBody
    @RequestMapping("/offLine")
    public Result offLine(@RequestBody HrPeople hrPeople) {
        return this.hrPeopleSvc.offLine(Lists.newArrayList(hrPeople.getPeopleEid()));
    }

}
