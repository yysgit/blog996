package com.yys.szcp.service;

import com.yys.szcp.entity.DbOrgan;

import java.util.List;

/**
 * Describe:
 * -------------------
 * User: yangyongsheng
 * Date: 2019/06/25 17:33:25
 * Email: 1095737364@qq.com
 */
public interface DbOrganService {

    /**
     * 添加机构
     * @param organ
     * @return
     */
    int addOrgan(DbOrgan organ);

    /**
     * 通过菜单id 获取菜单
     * @param organId
     * @return
     */
    DbOrgan findOrganById(Integer organId);


    /**
     * 通过机构父id获取子级机构列表
     * @param parentId
     * @return
     */
    List<DbOrgan> findOrganByParentId(Integer parentId);


    /**
     * 更新菜单
     * @param organ
     * @return
     */
    int updateOrgan(DbOrgan organ);
    int deleteOrgan(DbOrgan organ);

    /**
     * 根据名称查询机构
     * @param organId
     * @param parentId
     * @param organName
     * @return
     */
    List<DbOrgan> findOrganByName(Integer organId, Integer parentId, String organName);


    /**
     * 通过人员ID查询机构信息
     * @param organId
     * @return
     */
    DbOrgan findOrganByAdminUserId(Integer organId);

}
