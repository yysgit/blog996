package com.yys.szcp.service;


import com.yys.szcp.entity.DbAdminUser;

import java.util.List;
import java.util.Map;


public interface DbAdminUserService {
    //添加管理用户
    int addAdminUser(DbAdminUser adminUser);

    //分页通过机构id查询管理员用户
    List<Map> findAdminUserListByOrganId(Map map);
    int findAdminUserListByOrganIdCount(Map map);

    //初始化密码
    int editPasswordAdminUserInit(DbAdminUser adminUser);

    //通过id查询单个管理员用户详细信息
    Map findAdminUserINFOById(Integer adminUserId);

    //根据id 查询管理员用户返回用户对象
    DbAdminUser findAdminUserById(Integer adminUserId);

    // 登陆
    DbAdminUser login(String username, String password);

    //更新用户
    int updateAdminUser(DbAdminUser adminUser);
    int deleteAdminUser(DbAdminUser adminUser);

    //通过登陆名称查询单个管理员用户-->
    List<DbAdminUser> findAdminUserByAdminName(Integer adminUserId, String username);

}
