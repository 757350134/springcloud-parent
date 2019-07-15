package com.midea.service;

import com.midea.mapper.UserMapper;
import com.midea.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User queryById(Long id){
        return this.userMapper.selectById(id);
    }

    @Transactional
    public void deleteById(Long id){
        this.userMapper.deleteByPrimaryKey(id);
    }

    public List<User> queryAll(){
      return   this.userMapper.selectAll();
    }
}
