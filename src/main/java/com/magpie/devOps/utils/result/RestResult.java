package com.magpie.devOps.utils.result;

public class RestResult {
    public static final int CODE_FAIL = 400;

    public static final int CODE_SUCCESS = 200;

    public static final int CODE_REPEAT = 444;

    public static final int CODE_CONFIRM = 999;

    /**
     * 返回成功对象
     *
     * @param data
     * @return
     */
    public static Result success(Object data) {
        return Result.builder().code(CODE_SUCCESS).data(data).build();
    }

    /**
     * 返回失败对象
     *
     * @param msg
     * @return
     */
    public static Result fail(String msg) {
        return Result.builder().code(CODE_FAIL).msg(msg).build();
    }

    /**
     * 3.待确认
     *
     * @param msg
     * @return
     */
    public static Result confirm(String msg) {
        return Result.builder().code(CODE_CONFIRM).msg(msg).build();
    }

    /**
     * 3.待确认
     *
     * @param msg
     * @return
     */
    public static Result repeat(String msg) {
        return Result.builder().code(CODE_REPEAT).msg(msg).build();
    }
}
