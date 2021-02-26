package com.magpie.devOps.hr.svc;

import com.magpie.devOps.hr.entity.HrProject;
import com.magpie.devOps.utils.result.Result;

public interface HrProjectSvc {
    Result saveOrUpdate(HrProject hrProject);

    Result queryAll(HrProject hrProject);
}
