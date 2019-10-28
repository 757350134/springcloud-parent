package com.midea.proxy;

import com.midea.intercetor.RpcInvocationHandler;

import java.lang.reflect.Proxy;

public class ProxyFactory {

    public static  <T> T getProxy(Class<T> interfaceclass){
        return (T) Proxy.newProxyInstance(ProxyFactory.class.getClassLoader(),new Class[]{interfaceclass},new RpcInvocationHandler());
    }
}
