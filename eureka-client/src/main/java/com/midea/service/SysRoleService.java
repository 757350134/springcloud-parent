package com.midea.service;

import com.github.pagehelper.PageInfo;
import com.midea.config.BaseService;
import com.midea.model.SysRole;
import com.midea.model.base.ResponseVo;
import com.midea.model.vo.RoleConditionVo;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**

 **/
public interface SysRoleService extends BaseService<SysRole> {
    /***
     * 根据用户id查询角色列表
     * @param userId
     * @return
     */
    List<SysRole> listRolesByUserId(Integer userId);

    /***
     * 分页查询，使用分页插件
     * @param vo
     * @return
     */
    PageInfo<SysRole> findPageBreakByCondition(RoleConditionVo vo);

    /**
     * 根据用户id查询角色集合
     *
     * @param userId
     * @return set
     */
    Set<String> findRoleByUserId(Integer userId);

    ResponseVo addAssignResourceById(String roleId, List<String> resourceIds);

    /***
     * 根据用户id获取角色及选中的角色
     * @param userId
     * @return
     */
    List<Map<String, Object>> RoleListWithSelected(Integer userId);
}
