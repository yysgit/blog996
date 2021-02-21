package com.yys.szcp.entityVo;

import com.yys.szcp.entity.DbMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * Describe:
 * -------------------
 * User: yangyongsheng
 * Date: 2019/06/26 19:37:37
 * Email: 1095737364@qq.com
 */
public class MenuManageTreeUtil {
    public static List<MenuManageTree> getMenuManageTree(List<DbMenu> menuManageList) {
        List<MenuManageTree> menuManageTreeList = new ArrayList<>();
        for (int i = 0; i < menuManageList.size(); i++) {
            MenuManageTree menuManageTree = new MenuManageTree();
            DbMenu menu = menuManageList.get(i);

            //判断是否存在父级, 如果没有, 则表示是一级菜单
            if (menu.getParentId() == null || menu.getParentId() == 0) {
                menuManageTree.setIcon(menu.getIcon());
                menuManageTree.setId(menu.getId());
                menuManageTree.setTitle(menu.getTitle());
                menuManageTree.setSorting(menu.getSorting());
                menuManageTree.setParentId(menu.getParentId());
                menuManageTree.setPerms(menu.getPerms());
                menuManageTree.setHref(menu.getHref());
                menuManageTree.setGrade(1);
                menuManageTree.setChildren(getgetMenuManageTreeChild(menuManageList, menu.getId()));


                menuManageTreeList.add(menuManageTree);
            }
        }
        return menuManageTreeList;
    }

    private static List<MenuManageTree> getgetMenuManageTreeChild(List<DbMenu> menuManageList, Integer parentId) {
        List<MenuManageTree> menuTreeManageList = new ArrayList<>();
        for (int i = 0; i < menuManageList.size(); i++) {
            MenuManageTree menuManageTree = new MenuManageTree();
            DbMenu menu = menuManageList.get(i);
            if (menu.getParentId().equals(parentId)) {
                menuManageTree.setIcon(menu.getIcon());
                menuManageTree.setId(menu.getId());
                menuManageTree.setTitle(menu.getTitle());
                menuManageTree.setSorting(menu.getSorting());
                menuManageTree.setParentId(menu.getParentId());
                menuManageTree.setPerms(menu.getPerms());
                menuManageTree.setHref(menu.getHref());
                menuManageTree.setGrade(2);
                menuManageTree.setChildren(getgetMenuManageTreeChildThree(menuManageList, menu.getId()));
                menuTreeManageList.add(menuManageTree);
            }
        }
        return menuTreeManageList;
    }

    private static List<MenuManageTree> getgetMenuManageTreeChildThree(List<DbMenu> menuManageList, Integer parentId) {
        List<MenuManageTree> menuTreeManageList = new ArrayList<>();
        for (int i = 0; i < menuManageList.size(); i++) {
            MenuManageTree menuManageTree = new MenuManageTree();
            DbMenu menu = menuManageList.get(i);
            if (menu.getParentId().equals(parentId)) {
                menuManageTree.setIcon(menu.getIcon());
                menuManageTree.setId(menu.getId());
                menuManageTree.setTitle(menu.getTitle());
                menuManageTree.setSorting(menu.getSorting());
                menuManageTree.setParentId(menu.getParentId());
                menuManageTree.setPerms(menu.getPerms());
                menuManageTree.setHref(menu.getHref());
                menuManageTree.setGrade(3);
                menuTreeManageList.add(menuManageTree);
            }
        }
        return menuTreeManageList;
    }

}
