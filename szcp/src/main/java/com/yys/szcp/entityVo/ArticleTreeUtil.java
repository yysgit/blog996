package com.yys.szcp.entityVo;

import com.yys.szcp.entity.DbArticle;
import com.yys.szcp.entity.DbMenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: 徐万金·JimXu
 * @email: xuwanjin@jimxu.top
 * @Description:
 * @Date: Create in 17:00 2020-12-15
 */
public class ArticleTreeUtil {
    public static List<ArticleTree> getArticleTree(List<DbArticle> articleList){
        List<ArticleTree> articleTreelist=new ArrayList<>();
        for(int i=0;i<articleList.size();i++){
            ArticleTree articleTree=new ArticleTree();
            DbArticle article=articleList.get(i);

            //判断是否存在父级, 如果没有, 则表示是一级菜单
            if(article.getParentId()==null||article.getParentId()==0){
                articleTree.setId(article.getId());
                articleTree.setArticleId(article.getArticleId());
                articleTree.setDeleteStatus(articleTree.getDeleteStatus());
                articleTree.setLevel("1");
                articleTree.setRemark(article.getRemark());
                articleTree.setTitle(article.getTitle());
                articleTree.setUpdateTime(article.getUpdateTime());
                articleTree.setUpdateUser(article.getUpdateUser());
                articleTree.setChild(getgetArticleTreeChild(articleList,article.getId(),"1"));
                articleTreelist.add(articleTree);
            }
        }
        return articleTreelist;
    }

    private static List<ArticleTree> getgetArticleTreeChild(List<DbArticle> articleList, Integer parentId, String level){
        List<ArticleTree> articleTreeList=new ArrayList<>();
        for(int i=0;i<articleList.size();i++){
            ArticleTree articleTree=new ArticleTree();
            DbArticle article=articleList.get(i);
            if(article.getParentId().equals(parentId)){
                articleTree.setId(article.getId());
                articleTree.setArticleId(article.getArticleId());
                articleTree.setDeleteStatus(articleTree.getDeleteStatus());
                articleTree.setRemark(article.getRemark());
                articleTree.setTitle(article.getTitle());
                articleTree.setUpdateTime(article.getUpdateTime());
                articleTree.setUpdateUser(article.getUpdateUser());
                if("1".equals(level)){
                    articleTree.setLevel("2");
                    articleTree.setChild(getgetArticleTreeChild(articleList,article.getId(),"2"));
                }else{
                    articleTree.setLevel("3");
                }
                articleTreeList.add(articleTree);
            }
        }
        return articleTreeList;
    }
}
