package com.wxp.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: wxp
 * @Description:
 * @Date:Create：in 2019/11/8 9:24
 * @Modified By：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> implements Serializable {


    private int code;
    private String msg;
    private T data;

}
