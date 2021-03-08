package com.magpie.devOps.utils;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

@Slf4j
public class Utils {

    private static SimpleDateFormat SDF_YYYYMMDD = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat SDF_YMD_HMS = new SimpleDateFormat("yyyyMMddHHmm");
    // 计数器
    private static HashMap<String, Integer> COUNT_MAP = Maps.newHashMap();
    // 计数器补位长度
    private static final Integer COUNT_LENGTH = 8;
    // 补位字符
    private static final String PAD_CHAR = "0";
    public static String getDay() {
        return SDF_YYYYMMDD.format(new Date());
    }

    public static String getKey() {
        return getKey("KEY");
    }

    public static synchronized String getKey(String feature) {
        // 避免MAP中存储KEY太多,以日为单位
        StringBuilder result = new StringBuilder();
        result.append(feature);
        result.append(SDF_YMD_HMS.format(new Date()));

        Integer counter = COUNT_MAP.get(result.toString());
        if (Objects.isNull(counter)) {
            // 重新开始计数
            counter = 1;
        } else {
            counter++;
        }
        COUNT_MAP.put(result.toString(), counter);
        result.append(StringUtils.leftPad(String.valueOf(counter), COUNT_LENGTH, PAD_CHAR));
        return result.toString();
    }

    public static void main(String[] args) {
        log.info("key = {}" , getKey());
    }
}
