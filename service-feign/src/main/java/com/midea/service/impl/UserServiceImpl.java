package com.midea.service.impl;

import com.midea.service.HelloService;

import java.util.Date;

public class UserServiceImpl implements HelloService {
    @Override
    public String sayHi(String name) {
        return name;
    }

    @Override
    public String getUser() {
        return null;
    }

    @Override
    public Date getDate() {
        return null;
    }

    @Override
    public String queryUser() {
        return null;
    }
}
