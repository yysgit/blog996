package com.yys.szcp.mapper;

import com.yys.szcp.entity.DbOrgan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DbOrganMapper {

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

    /**
     * 删除机构
     * @param organ
     * @return
     */
    int deleteOrgan(DbOrgan organ);

    /**
     * 根据名称查询机构
     * @param organId
     * @param parentId
     * @param organName
     * @return
     */
    List<DbOrgan> findOrganByName(@Param("organId") Integer organId,@Param("parentId") Integer parentId, @Param("organName") String organName);

    /**
     * 通过人员ID查询机构信息
     * @param organId
     * @return
     */
    DbOrgan findOrganByAdminUserId(Integer organId);

}