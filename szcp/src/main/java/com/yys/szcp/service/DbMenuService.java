package com.yys.szcp.service;

import com.yys.szcp.entity.DbMenu;
import com.yys.szcp.entity.DbRoleMenu;

import java.util.List;

/**
 * Describe:
 * -------------------
 * User: yangyongsheng
 * Date: 2019/06/25 19:34:16
 * Email: 1095737364@qq.com
 */
public interface DbMenuService {

    /**
     * 添加菜单
     * @param menu
     * @return
     */
    int addMenu(DbMenu menu);

    /**
     * 通过菜单id 获取菜单
     * @param menuId
     * @return
     */
    DbMenu findMenuById(Integer menuId);

    /**
     * 通过机构父id获取子级机构列表
     * @param parentId
     * @return
     */
    List<DbMenu> findMenuByParentId(Integer parentId);

    /**
     *  通过菜单名称 得到的菜单
     * @param parentId
     * @return
     */
    List<DbMenu> findMenuByName(Integer menuId, Integer parentId, String title);

    /**
     * 更新菜单
     * @param menu
     * @return
     */
    int updateMenu(DbMenu menu);
    int deleteMenu(DbMenu menu);

    /**
     * 通过角色id 得到所有的菜单
     * @param roleId
     * @return
     */
    List<DbMenu> findMenuByRoleId(Integer roleId);


    /**添加角色菜单关系
     *
     * @param tRoleMenu
     * @return
     */
    int addTRoleMenu(DbRoleMenu tRoleMenu);


    /**
     * 根据角色id 删除菜单角色
     * @param roleId
     * @return
     */
    int deleteRoleMenuByTRoleId(Integer roleId);

}
