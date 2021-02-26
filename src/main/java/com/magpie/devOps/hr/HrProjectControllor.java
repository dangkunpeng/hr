package com.magpie.devOps.hr;

import com.magpie.devOps.hr.entity.HrProject;
import com.magpie.devOps.hr.svc.HrProjectSvc;
import com.magpie.devOps.utils.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping(method = RequestMethod.POST, value = "hr/project")
public class HrProjectControllor {
    @Resource
    private HrProjectSvc hrProjectSvc;

    @ResponseBody
    @RequestMapping("/list")
    public Result queryAll(@RequestBody HrProject hrProject) {
        return this.hrProjectSvc.queryAll(hrProject);
    }

    @ResponseBody
    @RequestMapping("/save")
    public Result save(@RequestBody HrProject hrProject) {
        return this.hrProjectSvc.saveOrUpdate(hrProject);
    }
}
