package com.midea.mapper;


import com.midea.config.BaseMapper;
import com.midea.model.SysUser;
import com.midea.model.vo.UserConditionVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program:hope-boot
 * @author:aodeng
 * @create:2018-10-16 13:59
 **/
@Mapper
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {
    List<SysUser> listUsersByRoleId(Integer roleId);

   List<SysUser> findPageBreakByCondition(UserConditionVo vo);

    SysUser selectUserByName(String userName);

    /**
     * 修改最后登录时间
     **/
    void updateLastLoginTime(SysUser sysUser);

    List<SysUser> findByRoleId(Integer roleId);

    int updateByUserId(SysUser sysUser);
}
