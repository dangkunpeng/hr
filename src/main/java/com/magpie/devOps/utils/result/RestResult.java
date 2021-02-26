package com.magpie.devOps.utils.result;

public class RestResult {
	/**
	 *  返回成功对象
	 * @param data
	 * @return
	 */
	public static Result success(Object data) {
		return new Result().setCode(Result.SUCCESS).setData(data);
	}
	/**
	 *  返回失败对象
	 * @param msg
	 * @return
	 */
	public static Result fail(String msg) {
		return new Result().setCode(Result.FAIL).setMsg(msg);
	}
	/**
	 * 3.待确认
	 * @param msg
	 * @return
	 */
	public static Result confirm(String msg) {
		return new Result().setCode(Result.CONFIRM).setMsg(msg);
	}
	/**
	 * 3.待确认
	 * @param msg
	 * @return
	 */
	public static Result repeat(String msg) {
		return new Result().setCode(Result.REPEAT).setMsg(msg);
	}
}
