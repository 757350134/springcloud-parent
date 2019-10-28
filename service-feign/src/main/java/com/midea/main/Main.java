package com.midea.main;

import com.midea.proxy.ProxyFactory;
import com.midea.service.HelloService;

public class Main {
    public static void main(String[] args) {


        HelloService service = ProxyFactory.getProxy(HelloService.class);

        String wxp = service.sayHi("wxp");

        System.out.println(wxp);
    }
}
