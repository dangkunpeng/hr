package com.magpie.devOps.hr.svc;

import com.magpie.devOps.hr.entity.HrPeople;
import com.magpie.devOps.utils.result.Result;

import java.util.List;

public interface HrPeopleSvc {
    Result saveOrUpdate(HrPeople hrPeople);

    Result offLine(List<String> ids);

    Result queryALl(HrPeople hrPeople);
}
