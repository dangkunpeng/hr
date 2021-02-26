package com.magpie.devOps.hr.svc;

import com.magpie.devOps.hr.entity.HrMenu;

import java.util.List;

public interface HrMenuSvc {

    List<HrMenu> getMenus(HrMenu hrMenu);
}
