package com.midea.intercetor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class RpcInvocationHandler implements InvocationHandler {
    private  Object obj;


    public  RpcInvocationHandler(Object obj){
        this.obj=obj;
    }

    public  RpcInvocationHandler(){

    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("拦截");
        return method.invoke(obj,args);
    }
}
