package com.midea.service;


import com.midea.config.BaseService;
import com.midea.model.SysResource;
import com.midea.model.SysRole;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @program:hope-boot

 **/
public interface SysResourceService extends BaseService<SysResource> {
    /***
     * 测试
     * @return
     */
    List<SysResource> selectAlls();

    /***
     * 获取url和permission
     * @return
     */
    List<SysResource> listUrlAndPermission();

    /***
     *获取用户关联的所有资源
     * @return
     */
    List<SysResource> listResourcesByUserId();

    /**
     * 根据用户id查询资源集合
     *
     * @param userId
     * @return set
     */
    Set<String> findPermsByUserId(Integer userId);

    /***
     * 获取角色资源
     * @param sysRole
     * @return
     */
    List<Map<String, Object>> roleResourceTreeData(SysRole sysRole);

    /***
     * 获取系统资源列表
     * @param sysResource
     * @return
     */
    List<SysResource> selectResourceList(SysResource sysResource);

    /***
     * 根据资源id查询数据
     * @param resourceId
     * @return
     */
    SysResource selectResourceById(Integer resourceId);

    /***
     * 获取所有资源数据
     * @return
     */
    List<Map<String, Object>> resourceTreeAll();

    /***
     * 根据id查看是否存在子级数据
     * @param resourceId
     * @return
     */
    int selectSubPermsById(Integer resourceId);
}
