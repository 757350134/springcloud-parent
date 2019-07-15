package com.midea.mapper;

import com.midea.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface UserMapper extends tk.mybatis.mapper.common.Mapper<User>{

    User selectById(@Param("id")Long id);
}