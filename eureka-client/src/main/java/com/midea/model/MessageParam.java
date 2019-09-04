package com.midea.model;

import javax.validation.constraints.NotBlank;

public class MessageParam {

    @NotBlank(message = "请输入参数")
    private String param;


   private String  paramA;

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getParamA() {
        return paramA;
    }

    public void setParamA(String paramA) {
        this.paramA = paramA;
    }
}
