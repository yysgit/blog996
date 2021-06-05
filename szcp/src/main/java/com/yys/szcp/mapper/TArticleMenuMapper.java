package com.yys.szcp.mapper;

import com.yys.szcp.entity.TArticle;
import com.yys.szcp.entity.TArticleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TArticleMenuMapper {


    /**
     * 添加文章菜单
     * @param articleMenu
     * @return
     */
    int addArticleMenu(TArticleMenu articleMenu);

    /**
     * 通过菜单id 获取菜单
     * @param articleMenuId
     * @return
     */
    TArticleMenu findArticleMenuById(Integer articleMenuId);

    /**
     * 通过机构父id获取子级机构列表
     * @param parentId
     * @return
     */
    List<TArticleMenu> findArticleMenuByParentId(Integer parentId);

    /**
     *  通过菜单名称 得到的菜单
     * @param parentId
     * @return
     */
    List<TArticleMenu> findArticleMenuByName(@Param("articleMenuId") Integer articleMenuId, @Param("parentId")Integer parentId,@Param("title") String title);

    /**
     * 更新文章菜单
     * @param articleMenu
     * @return
     */
    int updateArticleMenu(TArticleMenu articleMenu);

    /**
     * 删除文章菜单
     * @param articleMenu
     * @return
     */
    int deleteArticleMenu(TArticleMenu articleMenu);
    /**
     * 查询所有的文章菜单
     * @return
     */
    List<TArticleMenu> findArticleMenuList();

    /**
     * 查询菜单树的菜单
     * @param articleMenuId
     * @return
     */
    List<TArticle> findArticleByArticleMenuId(Integer articleMenuId);

    /**
     * home页面查询列表
     * @param articleMenuId
     * @return
     */
    List<TArticle> findArticleByArticleMenuIdForHome(Integer articleMenuId);


}