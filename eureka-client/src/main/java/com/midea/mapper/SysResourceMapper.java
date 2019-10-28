package com.midea.mapper;


import com.midea.config.BaseMapper;
import com.midea.model.SysResource;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**

 **/
@Mapper
@Repository
public interface SysResourceMapper extends BaseMapper<SysResource> {

    List<SysResource> selectResourceList(SysResource sysResource);

    List<SysResource> selectAlls();

    List<SysResource> listUrlAndPermission();

    List<SysResource> listResourcesByUserId();

    /**
     * 根据用户id查询资源集合
     *
     * @param userId 状态
     * @return set
     */
    Set<String> findPermsByUserId(Integer userId);

    /***
     * 根据角色id查询资源
     * @param roleId
     * @return
     */
    List<String> selectResourceTree(Integer roleId);

    SysResource selectResourceById(Integer resourceId);

    int selectSubPermsById(Integer resourceId);
}
