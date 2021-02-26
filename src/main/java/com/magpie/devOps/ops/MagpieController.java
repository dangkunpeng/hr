package com.magpie.devOps.ops;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.magpie.devOps.utils.Utils;

@RestController
// @Api(value = "Magpie")
@RequestMapping(value = "/magpie", method = { RequestMethod.POST, RequestMethod.GET })
public class MagpieController {

    @Autowired
    private MagpieService magpieService;
    @Autowired
    private MagpieRepo magpieRepo;

    // @ApiOperation(value = "query", notes = " query by day")

    // @ApiImplicitParam(name = "Magpie", dataType = "Magpie")
    @RequestMapping(value = "/query")
    public String query(@RequestBody MagpieForm form) {
        return this.magpieService.queryByDay(form);
    }

    // @ApiImplicitParam(name = "Magpie", dataType = "Magpie")
    @RequestMapping(value = "/save")
    public String save(@RequestBody Magpie magpie) {
        if (StringUtils.isBlank(magpie.getUuid())) {
            magpie.setUuid(Utils.getKey());
        }

        if (StringUtils.isBlank(magpie.getDay())) {
            magpie.setDay(Utils.getDay());
        }

        this.magpieService.update(magpie);
        return JSONObject.toJSONString(magpie);
    }

    // @ApiImplicitParam(name = "Magpie", dataType = "List")
    @RequestMapping(value = "/saveBatch")
    public String save(@RequestBody List<Magpie> list) {
        for (Magpie magpie : list) {
            this.save(magpie);
        }
        return JSONObject.toJSONString(list);
    }

    @RequestMapping(value = "/getDescs")
    public String getDescs() {
        return this.magpieService.getDescs();
    }

    // @ApiImplicitParam(name = "uuid", dataType = "String")
    @RequestMapping(value = "/del/{uuid}")
    public String del(@PathVariable String uuid) {
        this.magpieRepo.deleteById(uuid);
        return uuid;
    }

    // @ApiImplicitParam(name = "uuid", dataType = "List")
    @RequestMapping(value = "/delBatch")
    public String delBatch(@RequestBody List<Magpie> list) {
        this.magpieRepo.deleteInBatch(list);
        return "success";
    }
}
