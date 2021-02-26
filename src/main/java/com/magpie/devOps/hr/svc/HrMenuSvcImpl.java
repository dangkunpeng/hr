package com.magpie.devOps.hr.svc;

import com.magpie.devOps.hr.entity.HrMenu;
import com.magpie.devOps.hr.repo.HrMenuRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class HrMenuSvcImpl implements HrMenuSvc {
    @Resource
    private HrMenuRepo hrMenuRepo;

    @Override
    public List<HrMenu> getMenus(HrMenu hrMenu) {
        Example<HrMenu> example = Example.of(hrMenu);
        Sort sort = Sort.by(Sort.Order.asc("menuGroup"), Sort.Order.asc("menuIndex"));

        return this.hrMenuRepo.findAll(example, sort);
    }
}
