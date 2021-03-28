package com.yys.szcp.service;

import com.yys.szcp.entity.TArticle;

import java.util.List;
import java.util.Map;

public interface TArticleService {
    /**
     * 添加文章
     * @param article
     * @return
     */
    int addArticle(TArticle article);

    /**
     * 分页通过文章菜单id查询文章
     * @param map
     * @return
     */
    List<Map> findArticleListByArticleMenuId(Map map);
    int findArticleListByArticleMenuIdCount(Map map);



    /**
     * 更新文章
     * @param Article
     * @return
     */
    int updateArticle(TArticle Article);

    /**
     * 删除文章
     * @param Article
     * @return
     */
    int deleteArticle(TArticle Article);




    /**
     * 通过标题名称查询单个文章
     * @param articleId
     * @param title
     * @return
     */
    List<TArticle> findArticleByTitle(Integer articleId, String title);






    /**
     * 查询文档
     * @param path
     * @return
     */
    String getMarkdown(String path);

    /**
     * 更新文档
     * @param articleContent
     * @param path
     * @return
     */
    void saveMarkdown(String articleContent,String path);
}
