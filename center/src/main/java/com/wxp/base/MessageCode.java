package com.wxp.base;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author: wxp
 * @Description:
 * @Date:Create：in 2019/11/8 10:01
 * @Modified By：
 */
@NoArgsConstructor
@Getter
public enum  MessageCode {
    SUCCESS(200,"succes"),
    SERVER_EXCEPTION(500,"服务器异常");

    private int code;
    private String  message;

    MessageCode(int code,String message){
        this.code=code;
        this.message=message;
    }
}
