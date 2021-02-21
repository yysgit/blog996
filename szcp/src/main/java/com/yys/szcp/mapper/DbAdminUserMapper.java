package com.yys.szcp.mapper;

import com.yys.szcp.entity.DbAdminUser;
import org.apache.ibatis.annotations.Mapper;

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
  List<DbAdminUser> findAdminUserByUsername(Integer adminUserId, String username);

    /**
     * 初始化密码
     * @param adminUser
     * @return
     */
  int editPasswordAdminUserInit(DbAdminUser adminUser);


    /**
     * 增加新业务员金额
     * @param map
     * @return
     */
    int updateAdminUserForMoney(Map map);

    /**
     * 根据机构ID查询父类ID用户:如根据商户的机构ID 查询父类的商户利率
     * @param map
     * @return
     */
    List<DbAdminUser> findAdminUserByChildOrgan(Map map);

    /**
     * 通过机构Id查询最大子机构商户的管理用户
     * @param map
     * @return
     */
    List<DbAdminUser> findChildAdminUserByOrgan(Map map);
}