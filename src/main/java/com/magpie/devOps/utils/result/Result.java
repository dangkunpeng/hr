package com.magpie.devOps.utils.result;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class Result implements Serializable {

    private static final long serialVersionUID = 2290473005991832617L;

    private int code;

    private String msg;

    private Object data;

}