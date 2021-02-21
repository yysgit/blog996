package com.yys.szcp.mapper;

import com.yys.szcp.entity.DbMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DbMenuMapper {


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

    /**
     * 删除菜单
     * @param menu
     * @return
     */
    int deleteMenu(DbMenu menu);


    /**
     * 通过角色id 得到所有的菜单
     * @param roleId
     * @return
     */
    List<DbMenu> findMenuByRoleId(Integer roleId);
}