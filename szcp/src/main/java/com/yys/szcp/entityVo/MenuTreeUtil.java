package com.yys.szcp.entityVo;

import com.yys.szcp.entity.DbMenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Describe:
 * -------------------
 * User: yangyongsheng
 * Date: 2019/06/26 19:37:37
 * Email: 1095737364@qq.com
 */
public class MenuTreeUtil{
    public static List<MenuTree> getMenuTree(List<DbMenu> menuList){
        List<MenuTree> menuTreeList=new ArrayList<>();
        for(int i=0;i<menuList.size();i++){
            MenuTree menuTree=new MenuTree();
            DbMenu menu=menuList.get(i);

            //判断是否存在父级, 如果没有, 则表示是一级菜单
            if(menu.getParentId()==null||menu.getParentId()==0){


                if(menu.getHref()!=null&&!"".equals(menu.getHref())){


/*
{
        "icon":"",
        "name":"error_store",
        "meta":{
            "hideInBread":true
        },
        "children":[
            {
                "icon":"ios-bug",
                "name":"error_store_page",
                "meta":{
                    "icon":"ios-bug",
                    "title":"错误收集"
                }
            }
        ]
    }
 */
                    menuTree.setIcon(menu.getIcon());
                    menuTree.setName(menu.getTitle());
                    Map map= new HashMap();
                    map.put("hideInBread",true);
                    menuTree.setMeta(map);

                    MenuTree menuTreeChild=new MenuTree();
                    menuTreeChild.setIcon(menu.getIcon());
                    menuTreeChild.setName(menu.getHref());
                    Map mapChild= new HashMap();
                    mapChild.put("icon",menu.getIcon());
                    mapChild.put("title",menu.getTitle());
                    menuTreeChild.setMeta(mapChild);

                    List<MenuTree> menuTreeListChild=new ArrayList<>();
                    menuTreeListChild.add(menuTreeChild);

                    menuTree.setChildren(menuTreeListChild);

                }else{
                    menuTree.setIcon(menu.getIcon());
                    menuTree.setName(menu.getTitle());
                    Map map= new HashMap();
                    map.put("icon",menu.getIcon());
                    map.put("title",menu.getTitle());
                    menuTree.setMeta(map);
                    menuTree.setChildren(getgetMenuTreeChild(menuList,menu.getId()));

                }

                menuTreeList.add(menuTree);
            }
        }
        return menuTreeList;
    }

    private static List<MenuTree> getgetMenuTreeChild(List<DbMenu> menuList, Integer parentId){
        List<MenuTree> menuTreeList=new ArrayList<>();
        for(int i=0;i<menuList.size();i++){
            MenuTree menuTree=new MenuTree();
            DbMenu menu=menuList.get(i);
            if(menu.getParentId().equals(parentId)){
                menuTree.setIcon(menu.getIcon());
                menuTree.setName(menu.getHref());
                Map map= new HashMap();
                map.put("icon",menu.getIcon());
                map.put("title",menu.getTitle());
                menuTree.setMeta(map);
                menuTreeList.add(menuTree);
            }
        }
        return menuTreeList;
    }

}
