package com.yys.szcp.mapper;

import com.yys.szcp.entity.DbRoleMenu;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface DbRoleMenuMapper {


    /**
     * 删除菜单角色
     * @param roleMenu
     * @return
     */
    int deleteRoleMenuByTRoleMenu(DbRoleMenu roleMenu);

    /**
     * 添加菜单角色
     * @param roleMenu
     * @return
     */
    int addRoleMenu(DbRoleMenu roleMenu);

    /**
     * 根据角色id 删除菜单角色
     * @param roleId
     * @return
     */
    int deleteRoleMenuByTRoleId(Integer roleId);
}