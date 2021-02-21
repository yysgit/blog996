package com.yys.szcp.service;

import com.yys.szcp.entity.DbRole;

import java.util.List;
import java.util.Map;

/**
 * Describe:
 * -------------------
 * User: yangyongsheng
 * Date: 2019/06/25 16:33:56
 * Email: 1095737364@qq.com
 */
public interface DbRoleService {

    /**
     * 添加角色
     * @param role
     * @return
     */
    int addRole(DbRole role);

    /**
     * 查询角色列表
     * @param map
     * @return
     */
    List<Map> findRoleList(Map map);
    int findRoleListCount(Map map);

    List<Map> findRoleAllList(Map map);

    /**
     * 根据id 查询角色
     * @param roleId
     * @return
     */
    DbRole findRoleById(Integer roleId);

    /**
     * 更新角色
     * @param role
     * @return
     */
    int updateRole(DbRole role);
    int deleteRole(DbRole role);

    /**
     * 根据名称查询角色
     * @param roleId
     * @param roleName
     * @return
     */
    List<DbRole> findRoleByName(Integer roleId, String roleName);

}
