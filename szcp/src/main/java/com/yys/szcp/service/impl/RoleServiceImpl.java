package com.yys.szcp.service.impl;

import com.yys.szcp.entity.DbRole;
import com.yys.szcp.mapper.DbRoleMapper;
import com.yys.szcp.service.DbRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Describe:
 * -------------------
 * User: yangyongsheng
 * Date: 2019/06/25 16:34:58
 * Email: 1095737364@qq.com
 */
@Service
public class RoleServiceImpl implements DbRoleService {
    @Autowired
    private DbRoleMapper roleMapper;

    @Override
    public int addRole(DbRole role) {
        return roleMapper.addRole(role);
    }

    @Override
    public List<Map> findRoleList(Map map) {
        return roleMapper.findRoleList(map);
    }

    @Override
    public int findRoleListCount(Map map) {
        return roleMapper.findRoleListCount(map);
    }


    @Override
    public List<Map> findRoleAllList(Map map) {
        return  roleMapper.findRoleAllList(map);
    }

    @Override
    public DbRole findRoleById(Integer roleId) {
        return roleMapper.findRoleById(roleId);
    }

    @Override
    public int updateRole(DbRole role) {
        return roleMapper.updateRole(role);
    }

    @Override
    public int deleteRole(DbRole role) {
        return roleMapper.deleteRole(role);
    }

    @Override
    public List<DbRole> findRoleByName(Integer roleId,String roleName){
        return roleMapper.findRoleByName(roleId,roleName);
    }
}
