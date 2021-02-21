package com.yys.szcp.service.impl;

import com.yys.szcp.entity.DbMenu;
import com.yys.szcp.entity.DbRoleMenu;
import com.yys.szcp.mapper.DbMenuMapper;
import com.yys.szcp.mapper.DbRoleMenuMapper;
import com.yys.szcp.service.DbMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Describe:
 * -------------------
 * User: yangyongsheng
 * Date: 2019/06/25 19:34:53
 * Email: 1095737364@qq.com
 */
@Service
public class MenuServiceImpl implements DbMenuService {
    @Autowired
    private DbMenuMapper menuMapper;
    @Autowired
    private DbRoleMenuMapper roleMenuMapper;

    @Override
    public int addMenu(DbMenu menu) {
        return menuMapper.addMenu(menu);
    }

    @Override
    public DbMenu findMenuById(Integer menuId) {
        return menuMapper.findMenuById(menuId);
    }

    @Override
    public List<DbMenu> findMenuByParentId(Integer parentId) {
        return menuMapper.findMenuByParentId(parentId);
    }

    @Override
    public List<DbMenu> findMenuByName(Integer menuId, Integer parentId, String title) {
        return menuMapper.findMenuByName(menuId,parentId,title);
    }

    @Override
    public int updateMenu(DbMenu menu) {
        return menuMapper.updateMenu(menu);
    }

    @Override
    public int deleteMenu(DbMenu menu) {
        return menuMapper.deleteMenu(menu);
    }

    @Override
    public List<DbMenu> findMenuByRoleId(Integer roleId) {
        return menuMapper.findMenuByRoleId(roleId);
    }

    @Override
    public int addTRoleMenu(DbRoleMenu roleMenu) {
        return roleMenuMapper.addRoleMenu(roleMenu);
    }

    @Override
    public int deleteRoleMenuByTRoleId(Integer roleId) {
        return roleMenuMapper.deleteRoleMenuByTRoleId(roleId);
    }
}
