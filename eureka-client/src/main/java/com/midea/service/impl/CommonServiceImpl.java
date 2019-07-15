package com.midea.service.impl;

import com.midea.mapper.UserMapper;
import com.midea.model.User;
import com.midea.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommonServiceImpl implements CommonService {
    @Override
    public Date getDate() {
        return new Date();
    }
    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryUser() {
        return userMapper.selectById(6L);
    }
}
