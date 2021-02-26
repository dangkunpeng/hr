package com.magpie.devOps.ops;

import com.magpie.devOps.common.QueryParam;

import lombok.Data;

@Data
public class MagpieForm extends QueryParam {
    private String uuid;
    private String day;
    private String desc;
}
