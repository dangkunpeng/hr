package com.magpie.devOps.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

public class Utils {

    private static SimpleDateFormat SDF_YYYYMMDD = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat SDF_YMD_HMS = new SimpleDateFormat("yyyyMMddHHmmss");


    public static String getDay() {
        return SDF_YYYYMMDD.format(new Date());
    }

    public static String getKey() {
        return SDF_YMD_HMS.format(new Date()) + "_" +StringUtils.remove(UUID.randomUUID().toString(),"-");
    }
}
