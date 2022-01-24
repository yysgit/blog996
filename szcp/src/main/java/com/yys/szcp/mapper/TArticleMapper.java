package com.yys.szcp.mapper;

import com.yys.szcp.entity.TArticle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface TArticleMapper {
    /**
     * 添加文章
     *
     * @param article
     * @return
     */
    int addArticle(TArticle article);

    /**
     * 分页通过文章菜单id查询文章
     *
     * @param map
     * @return
     */
    List<Map> findArticleListByArticleMenuId(Map map);


    int findArticleListByArticleMenuIdCount(Map map);

    /**
     * 搜索文章
     * @param map
     * @return
     */
    List<Map> findArticleListByArticleNameSearch(Map map);

    /**
     * 更新文章
     *
     * @param article
     * @return
     */
    int updateArticle(TArticle article);

    /**
     * 删除文章
     *
     * @param article
     * @return
     */
    int deleteArticle(TArticle article);

    /**
     * 更新文章访问次数
     *
     * @param url
     * @return
     */
    int updateArticleViewsNumber(String url);


    /**
     * 通过标题名称查询单个文章
     *
     * @param articleId
     * @param title
     * @return
     */
    List<TArticle> findArticleByTitle(@Param("articleId") Integer articleId, @Param("title") String title);

    /**
     * 通过url查询单个文章
     *
     * @param url
     * @return
     */
    TArticle findArticleByUrl(@Param("url") String url);


}
