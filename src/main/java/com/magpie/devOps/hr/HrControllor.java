package com.magpie.devOps.hr;

import com.magpie.devOps.hr.entity.HrMenu;
import com.magpie.devOps.hr.svc.HrMenuSvc;
import com.magpie.devOps.hr.svc.HrPeopleSvc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping(method = RequestMethod.GET, value = "hr")
public class HrControllor {
    @Resource
    private HrMenuSvc hrMenuSvc;

    @Resource
    private HrPeopleSvc hrPeopleSvc;

    @RequestMapping("/index")
    public String main(ModelMap model) {
        List<HrMenu> list = this.hrMenuSvc.getMenus(HrMenu.builder().build());
        Map<String, List<HrMenu>> menuGroup = list.stream().collect(Collectors.groupingBy(HrMenu::getMenuGroup));
        model.put("menuGroup", menuGroup);
        model.put("theme", "helloworld");
        return "hr/index";
    }

    @RequestMapping("/people/init")
    public String peopleInit(ModelMap model) {
        model.put("theme", "helloworld");
        return "hr/people";
    }
}
