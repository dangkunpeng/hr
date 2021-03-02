package com.magpie.devOps.hr.svc;

import com.google.common.collect.Lists;
import com.magpie.devOps.hr.entity.HrPeople;
import com.magpie.devOps.hr.entity.HrProject;
import com.magpie.devOps.hr.repo.HrPeopleRepo;
import com.magpie.devOps.hr.repo.HrProjectRepo;
import com.magpie.devOps.hr.view.PeopleProjectView;
import com.magpie.devOps.utils.result.RestResult;
import com.magpie.devOps.utils.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class HrPeopleSvcImpl implements HrPeopleSvc {
    @Resource
    private HrPeopleRepo hrPeopleRepo;

    @Resource
    private HrProjectRepo hrProjectRepo;

    @Override
    public Result saveOrUpdate(HrPeople hrPeople) {
        log.info("新增记录");
        hrPeople.setStatus("online");

        this.hrPeopleRepo.save(hrPeople);

        return RestResult.success(hrPeople.getPeopleEid());
    }

    @Override
    public Result offLine(List<String> ids) {
        List<HrPeople> list = this.hrPeopleRepo.findAllById(ids);
        list.forEach(hrPeople -> hrPeople.setStatus("offline"));
        this.hrPeopleRepo.saveAll(list);
        return RestResult.success(ids);
    }

    @Override
    public Result queryALl(HrPeople hrPeople) {
        Example<HrPeople> example = Example.of(hrPeople);
        Sort sort = Sort.by(Sort.Order.desc("status"), Sort.Order.asc("peopleEid"));
        List<HrPeople> peopleList = this.hrPeopleRepo.findAll(example, sort);
        Sort projectSort = Sort.by(Sort.Order.desc("projectId"));
        List<HrProject> projectList = this.hrProjectRepo.findAll(projectSort);
        Map<String, List<HrProject>> projectMap = projectList.stream().collect(Collectors.groupingBy(HrProject::getPeopleEid));

        List<PeopleProjectView> result = Lists.newArrayList();
        for (HrPeople people : peopleList) {
            PeopleProjectView view = new PeopleProjectView();
            BeanUtils.copyProperties(people, view);
            List<HrProject> projectTemp = projectMap.get(people.getPeopleEid());
            if (!CollectionUtils.isEmpty(projectTemp)) {
                BeanUtils.copyProperties(projectTemp.get(0), view);
            }
            result.add(view);
        }
        return RestResult.success(result);
    }
}
