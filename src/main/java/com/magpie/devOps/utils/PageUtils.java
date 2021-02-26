package com.magpie.devOps.utils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.alibaba.fastjson.JSONObject;
import com.magpie.devOps.common.QueryParam;
import com.magpie.devOps.utils.result.PageResult;

public class PageUtils {

	/**
	 * 分页
	 * @param param
	 * @return
	 */
	public static Pageable newPageQuery(QueryParam param) {
		Pageable pageable = PageRequest.of(getPage(param), param.getLimit());
		return pageable;
	}

	/**
	 * 分页并且排序
	 * @param param
	 * @param sort
	 * @return
	 */
	public static Pageable newPageQuery(QueryParam param, Sort sort) {
		Pageable pageable = PageRequest.of(getPage(param), param.getLimit(), sort);
		return pageable;
	}

	/**
	 * 转换JPA的page来适应bootstrap的page
	 * 
	 * @param <T>
	 * @param page
	 * @return
	 */
	public static <T> String newPage(Page<T> page) {
		PageResult<T> result = new PageResult<T>();
		result.setRows(page.getContent());
		result.setTotal(page.getTotalElements());
		return JSONObject.toJSONString(result);
	}


	/**
	 * 计算分页
	 * @param param
	 * @return
	 */
	public static Integer getPage(QueryParam param) {
		if (0 == param.getLimit()) {
			return 0;
		} else {
			return param.getOffset() / param.getLimit();
		}
	}
}
