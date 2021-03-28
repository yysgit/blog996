package com.yys.szcp.mapper;

import com.yys.szcp.entity.DbAdminUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface DbAdminUserMapper {


    /**
     * 添加管理员用户
     * @param adminUser
     * @return
     */
    int addAdminUser(DbAdminUser adminUser);

    /**
     * 分页通过机构id查询管理员用户
     * @param map
     * @return
     */
    List<Map> findAdminUserListByOrganId(Map map);
    int findAdminUserListByOrganIdCount(Map map);

    /**
     * 登陆查询
     * @param map
     * @return
     */
    List<DbAdminUser> findAdminUserByUsernamePassword(Map map);

    /**
     * 更新管理员用户
     * @param adminUser
     * @return
     */
    int updateAdminUser(DbAdminUser adminUser);
    int deleteAdminUser(DbAdminUser adminUser);

    /**
     * 根据id 查询管理员用户返回用户对象
     * @param adminUserId
     * @return
     */
    DbAdminUser findAdminUserById(Integer adminUserId);


    /**
     * 通过id查询单个管理员用户详细信息
     * @param adminUserId
     * @return
     */
    Map findAdminUserINFOById(Integer adminUserId);


    /**
     * 通过登陆名称查询单个管理员用户
     * @param adminUserId
     * @param username
     * @return
     */
  List<DbAdminUser> findAdminUserByAdminName(@Param("adminUserId") Integer adminUserId,@Param("username") String username);

    /**
     * 初始化密码
     * @param adminUser
     * @return
     */
  int editPasswordAdminUserInit(DbAdminUser adminUser);




}