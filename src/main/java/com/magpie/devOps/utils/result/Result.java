package com.magpie.devOps.utils.result;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;


public class Result implements Serializable {

	private static final long serialVersionUID = 2290473005991832617L;

	public static final int FAIL = 400;
	
	public static final int SUCCESS = 200;
	
	public static final int REPEAT = 444;
	
	public static final int CONFIRM = 999;
	private int code;
	
	private String msg;
	
	private Object data;

	public int getCode() {
		return code;
	}

	public Result setCode(int code) {
		this.code = code;
		
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public Result setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	public Object getData() {
		return data;
	}

	public Result setData(Object data) {
		this.data = data;
		return this;
	}
	
	public boolean success() {
		return this.code == SUCCESS;
	}
	public boolean fail() {
		return this.code != SUCCESS;
	}
	
	public String toString() {
		
		return JSONObject.toJSONString(this);
	}
}