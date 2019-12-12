package com.wxp.base;

/**
 * @Author: wxp
 * @Description:
 * @Date:Create：in 2019/11/10 11:39
 * @Modified By：
 */
public class ResultUtil {

    public static Result success(Object object) {
        Result result = new Result();

        result.setCode(MessageCode.SUCCESS.getCode());
        result.setMsg(MessageCode.SUCCESS.getMessage());
        result.setData(object);
        return result;
    }

    public static Result success() {
        return success(null);
    }


    public static Result error(MessageCode msgCode) {
        Result result = new Result();
        result.setCode(msgCode.getCode());
        result.setMsg(msgCode.getMessage());
        return result;
    }
}
