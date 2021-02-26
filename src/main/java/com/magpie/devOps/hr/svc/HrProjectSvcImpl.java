package com.magpie.devOps.hr.svc;

import com.magpie.devOps.hr.entity.HrProject;
import com.magpie.devOps.hr.repo.HrProjectRepo;
import com.magpie.devOps.utils.Utils;
import com.magpie.devOps.utils.result.RestResult;
import com.magpie.devOps.utils.result.Result;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class HrProjectSvcImpl implements HrProjectSvc {

    @Resource
    private HrProjectRepo hrProjectRepo;

    @Override
    public Result saveOrUpdate(HrProject hrProject) {
        hrProject.setProjectId(Utils.getKey());
        this.hrProjectRepo.save(hrProject);
        return RestResult.success(hrProject.getProjectId());
    }

    @Override
    public Result queryAll(HrProject hrProject) {
        Example<HrProject> example = Example.of(hrProject);
        Sort sort = Sort.by(Sort.Order.desc("projectId"));
        return RestResult.success(this.hrProjectRepo.findAll(example, sort));
    }
}
