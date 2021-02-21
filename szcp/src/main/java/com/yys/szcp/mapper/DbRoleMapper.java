package com.yys.szcp.mapper;

import com.yys.szcp.entity.DbRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DbRoleMapper {

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

    /**
     * 查询角色列表
     * @param map
     * @return
     */
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

    /**
     * 删除角色
     * @param role
     * @return
     */
    int deleteRole(DbRole role);

    /**
     * 根据名称查询角色
     * @param roleId
     * @param roleName
     * @return
     */
    List<DbRole> findRoleByName(Integer roleId, String roleName);
}