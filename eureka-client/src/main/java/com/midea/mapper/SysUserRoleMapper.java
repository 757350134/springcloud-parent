package com.midea.mapper;


import com.midea.config.BaseMapper;
import com.midea.model.SysUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @program:hope-boot
 **/
@Mapper
@Repository
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
}
